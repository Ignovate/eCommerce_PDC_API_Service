<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="com.erp.payment.plugins.support.bitmap.*" %>
<%@ page import="java.util.*" %>
<html>
<title>NI Bitmap Payment Message Integration Sample</title>
<body style="border:5px solid red">
<%
    BitmapMessage message = (BitmapMessage)request.getAttribute("message");

        out.println("Decrypted Message: <br/>" + message.build());
        out.println("<br/><br/>");

        String value = message.block(DataType.Response.Block.ADDITIONAL_INFO).getField("CardNumber");
        out.println("CardNumber : " + value + "<br/>");
        value = message.block(DataType.Response.Block.ADDITIONAL_INFO).getField("CardToken");
        out.println("CardToken : " + value + "<br/>");

        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("Amount");
        out.println("Amount : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("PayModeType");
        out.println("PayModeType : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("TransactionType");
        out.println("TransactionType : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("Currency");
        out.println("Currency : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("MerchantOrderNumber");
        out.println("MerchantOrderNumber : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE).getField("CardType");
        out.println("CardType : " + value + "<br/>");

        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("GatewayTraceNumber");
        out.println("GatewayTraceNumber : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("TransactionDate");
        out.println("TransactionDate : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("CardEnrollmentResponse");
        out.println("CardEnrollmentResponse : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("ECIIndicator");
        out.println("ECIIndicator : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("AuthCode");
        out.println("AuthCode : " + value + "<br/>");
        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_RELATED).getField("ReferenceNumber");
        out.println("ReferenceNumber : " + value + "<br/>");

        value = message.block(DataType.Response.Block.TRANSACTION_RESPONSE_STATUS).getField("StatusFlag");
        out.println("StatusFlag : " + value + "<br/>");
        value = message.block(DataType.Response.Block.ADDITIONAL_INFO).getField("ErrorMessage");
        out.println("ErrorMessage : " + value + "<br/>");
        value = message.block(DataType.Response.Block.ADDITIONAL_INFO).getField("ErrorCode");
        out.println("ErrorCode : " + value + "<br/>");

        value = message.block(DataType.Response.Block.DCC_RESPONSE).getField("DCCConverted");
        out.println("DCCConverted : " + value + "<br/><br/>");


        for(Map.Entry<String, BitmapMessage.BlockData> entry : message.toBlockMap().entrySet()){
            out.println("=========== Start Block: " + entry.getKey() + " ================<br/>");
            for(Map.Entry<String, String> blockEntry : entry.getValue().toMap().entrySet()){
                out.println(blockEntry.getKey() + " : " + blockEntry.getValue() + "<br/>");
            }
            out.println("=========== End Block: " + entry.getKey() + " ================<br/>");
            out.println("<br/>");
        }

%>
<button name="backHome" onclick="window.location.href='/ni-bitmap-moto-java-sample/moto/'">Back Home</button>
</body>
</html>
