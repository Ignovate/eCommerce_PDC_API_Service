package com.gaia.web.rest;

import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

	/*private static String MID = "201803211000005";
	private static String MKEY = "pxD82WSZsGMWAiSHZsYQyE3f5Lfgh5PTDX5GFylMTYg=";
	private static String DATE_FORMAT = "dd-MMM-yyyy hh:mm:ss S"; // 30-Dec-2018 02:23:09 PM

	@Autowired
	private SalesQuoteService salesQuoteService;
	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private SalesQuotePaymentService salesQuotePaymentService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "failed.jsp";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView payment1(ModelMap model, HttpSession session, @RequestParam(value = "quoteId") Long quoteId) {
		if (quoteId != null) {
			SalesQuote salesQuote = salesQuoteService.getSalesQuote(quoteId);
			session.setAttribute("quoteId", salesQuote.getId());
			if (salesQuote != null) {
				PaymentReqVm paymentReq = new PaymentReqVm();
				paymentReq.setCustomerId(salesQuote.getCustomerId());
				paymentReq.setQuoteId(salesQuote.getId());
				paymentReq.setCurrency("AED");
				paymentReq.setAmount(salesQuote.getGrandTotal());
				return payment(model, session, paymentReq);
			}
		}

		return new ModelAndView("failed");
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public ModelAndView payment(ModelMap model, HttpSession session, PaymentReqVm paymentReq) {
		// session.invalidate();

		session.setAttribute("MID", MID);
		session.setAttribute("MKEY", MKEY);
		session.setAttribute("quoteId", paymentReq.getQuoteId());

		model.addAttribute("endPointLookup", TransactionEndPoint.values());
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
		UriComponents currentUri = builder.build();
		String pageUrl = currentUri.getScheme().concat("://").concat(currentUri.getHost())
				.concat(":" + currentUri.getPort()).concat("/gaia-ecom-service");

		System.out.println("pageurl" + pageUrl);
		BitmapMessage message = createMessage(DataType.REQUEST);
		message = buildPaymentRequest(message, pageUrl, paymentReq);

		message.validate();

		if (message.getMessages().length() > 0) {

		} else {
			// Print Payment Request
			printParams(message);

			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(message.getTransactionURL());
			Map<String, Object> param = new HashMap<String, Object>();
			model.put("requestParameter", message.build(Boolean.TRUE));
			return new ModelAndView(redirectView, "model", param);
		}
		return new ModelAndView("failed");
	}

	@RequestMapping(value = "/payment/success", method = RequestMethod.POST)
	public ModelAndView paymentSuccess(ModelMap model, HttpServletRequest request, HttpSession session)
			throws JsonProcessingException {

		String mid = (String) session.getAttribute("MID");
		String mKey = (String) session.getAttribute("MKEY");
		Long quoteId = (Long) session.getAttribute("quoteId");

		BitmapMessage message = createMessage(DataType.RESPONSE);
		message.parseMessage(request.getParameter("responseParameter"), Boolean.TRUE);
		// Print Payment Response
		printParams(message);
		// Save Response status
		PaymentRespVm paymentResp = parsePaymentResponse(message);
		salesQuotePaymentService.addSalesQuotePay(buildPayment(paymentResp));
		String res = cartService.saleOrder(Long.valueOf(paymentResp.getQuoteId()));
		if (!"Success".equals(res))
			System.out.println("Sales Order insert failed");
		
		session.setAttribute("status", "Success");
		session.setAttribute("response", paymentResp);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("http://localhost:4200/#/payment");
		return new ModelAndView(redirectView);
	}

	@RequestMapping(value = "/payment/failure", method = RequestMethod.POST)
	public void paymentFailure(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
			throws IOException {

		String mid = (String) session.getAttribute("MID");
		String mKey = (String) session.getAttribute("MKEY");

		BitmapMessage message = createMessage(DataType.RESPONSE);
		message.parseMessage(request.getParameter("responseParameter"), Boolean.TRUE);
		// Print Payment Response
		printParams(message);
		// Save Response status
		PaymentRespVm paymentResp = parsePaymentResponse(message);
		salesQuotePaymentService.addSalesQuotePay(buildPayment(paymentResp));
		session.setAttribute("status", "Failed");
		session.setAttribute("response", paymentResp);

//		RedirectView redirectView = new RedirectView();
//		redirectView.setUrl("http://localhost:4200/#/payment");
//		return new ModelAndView(redirectView);
		String url = response.encodeRedirectURL(
				   "http://localhost:4200/#/payment");
		response.sendRedirect(url);
	}

	@RequestMapping(value = "/api/v1.0/payment/status", method = RequestMethod.GET)
	@ResponseBody
	public PaymentRespVm paymentStatus(HttpServletRequest request, HttpSession session) {
		PaymentRespVm paymentResp = new PaymentRespVm();
		paymentResp.setStatus((String) session.getAttribute("status"));

		return paymentResp;
	}

	private BitmapMessage createMessage(DataType requestType) {
		BitmapMessage message = new BitmapMessage(requestType);

		// Configure Bitmap Message
		message.transactionEndPoint(TransactionEndPoint.TEST).merchantId(MID).encKey(MKEY);
		return message;
	}

	private BitmapMessage buildPaymentRequest(BitmapMessage message, String pageUrl, PaymentReqVm paymentReq) {
		message.block(DataType.Request.Block.TRANSACTION)
				.setField(DataType.TransactionBlockField.TransactionType, TransactionType.SALE.getStringValue())
				.setField(DataType.TransactionBlockField.TransactionMode, TransactionMode.MOTO)
				.setField(DataType.TransactionBlockField.PayModeType, PaymentModeType.CreditCard)
				.setField(DataType.TransactionBlockField.Currency, paymentReq.getCurrency())
				.setField(DataType.TransactionBlockField.MerchantOrderNumber, "DOC" + new Date().getTime())
				.setField(DataType.TransactionBlockField.Amount, paymentReq.getAmount().toString())
				.setField(DataType.TransactionBlockField.SuccessUrl, pageUrl + "/payment/success")
				.setField(DataType.TransactionBlockField.FailureUrl, pageUrl + "/payment/failure")
				.done()
//				.block(DataType.Request.Block.BILLING)
//				.setField(DataType.BillingBlockField.BillToFirstName, "mano")
//				.setField(DataType.BillingBlockField.BillToLastName, "s")
//				.setField(DataType.BillingBlockField.BillToStreet1, "street")
//				.setField(DataType.BillingBlockField.BillToStreet2, "street2")
//				.setField(DataType.BillingBlockField.BillToCity, "city")
//				.setField(DataType.BillingBlockField.BillToState, "state")
//				.setField(DataType.BillingBlockField.BillToPostalCode, "12345")
//				.setField(DataType.BillingBlockField.BillToCountry, "AE")
//				.setField(DataType.BillingBlockField.BillToEmail, "mano@gmail.com")
//				.setField(DataType.BillingBlockField.BillToMobileNumber, "9790012345")
//				.setField(DataType.BillingBlockField.BillToPhoneNumber1, "9790012345")
//				.setField(DataType.BillingBlockField.BillToPhoneNumber2, "9790012345")
//				.setField(DataType.BillingBlockField.BillToPhoneNumber3, "9790012345")
//				.done()
				.block(DataType.Request.Block.MERCHANT)
				.setField(DataType.MerchantBlockField.UDF1, String.valueOf(paymentReq.getCustomerId()))
				.setField(DataType.MerchantBlockField.UDF2, String.valueOf(paymentReq.getQuoteId()))
				.done();
		return message;
	}

	private PaymentRespVm parsePaymentResponse(BitmapMessage message) {
		PaymentRespVm payResp = new PaymentRespVm();
		// TRANSACTION_RESPONSE_STATUS
		BlockData statusBlock = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_STATUS);
		payResp.setStatus(statusBlock.getField(DataType.TransactionResponseStatusBlockField.StatusFlag));
		payResp.setErrorCode(statusBlock.getField(DataType.TransactionResponseStatusBlockField.ErrorCode));
		payResp.setErroMessage(statusBlock.getField(DataType.TransactionResponseStatusBlockField.ErrorMessage));

		// TRANSACTION_RESPONSE
		BlockData respBlock = message.block(DataType.Response.Block.TRANSACTION_RESPONSE);
		payResp.setCurrency(respBlock.getField(DataType.TransactionResponseBlockField.Currency));
		String amount = respBlock.getField(DataType.TransactionResponseBlockField.Amount);
		if(amount != null) {
			payResp.setAmount(new BigDecimal(amount));
		}
		payResp.setPaymentModeType(respBlock.getField(DataType.TransactionResponseBlockField.PayModeType));
		payResp.setTransactionType(respBlock.getField(DataType.TransactionResponseBlockField.TransactionType));
		payResp.setCardType(respBlock.getField(DataType.TransactionResponseBlockField.CardType));
		payResp.setMerchantOrderNumber(respBlock.getField(DataType.TransactionResponseBlockField.MerchantOrderNumber));

		// TRANSACTION_RESPONSE_RELATED
		BlockData respRelBlock = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED);
		payResp.setGatewayTraceNumber(
				respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.GatewayTraceNumber));
		payResp.setCardEnrollmentResponse(
				respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.CardEnrollmentResponse));
		payResp.setEciIndicator(respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.ECIIndicator));
		payResp.setAuthCode(respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.AuthCode));
		payResp.setReferenceNumber(
				respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.ReferenceNumber));
		String transactionDate = respRelBlock.getField(DataType.TransactionResponseRelatedBlockField.TransactionDate);
		if(transactionDate != null) {
			LocalDateTime dateTime = LocalDateTime.parse(transactionDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
			payResp.setTransactionDate(dateTime);	
		}
		// MERCHANT
		BlockData merchantBlock = message.block(DataType.Response.Block.MERCHANT);
		payResp.setCustomerId(merchantBlock.getField(DataType.MerchantBlockField.UDF1));
		payResp.setQuoteId(merchantBlock.getField(DataType.MerchantBlockField.UDF2));

		return payResp;
	}

	private void printParams(BitmapMessage message) {
		System.out.println("Start");
		for (Map.Entry<String, BitmapMessage.BlockData> entry : message.toBlockMap().entrySet()) {
			System.out.println("=========== Start Block: " + entry.getKey() + " ================");
			for (Map.Entry<String, String> blockEntry : entry.getValue().toMap().entrySet()) {
				System.out.println(blockEntry.getKey() + " : " + blockEntry.getValue());
			}
			System.out.println("=========== End Block: " + entry.getKey() + " ================");
			System.out.println("");
		}
		System.out.println("End");
	}
	
	private SalesQuotePayment buildPayment(PaymentRespVm paymentResp) {
		SalesQuotePayment payment = new SalesQuotePayment();
		payment.setQuoteId(Long.valueOf(paymentResp.getQuoteId()));
		payment.setCcType(paymentResp.getPaymentModeType());
		payment.setMethod(paymentResp.getTransactionType());
		return payment;
	}*/

}
