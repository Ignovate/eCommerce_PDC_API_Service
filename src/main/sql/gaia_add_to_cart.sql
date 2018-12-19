CREATE PROCEDURE igecom.gaia_add_to_cart_p(
	IN quoteId INT,
	IN customerId INT,
	IN productId INT,
	IN quantity INT,
	IN websiteId INT,
	IN addressId INT,
	IN actionType VARCHAR(24),
	OUT o_quoteId INT
)
BEGIN
	DECLARE grandTotal DECIMAL DEFAULT 0;
	DECLARE price DECIMAL DEFAULT 0;
	DECLARE specialPrice DECIMAL DEFAULT 0;

	
	SELECT COALESCE(price, 0), COALESCE(special_price, 0) INTO price, specialPrice FROM products_price WHERE product_id = P_PROD_ID;
	
	IF (quoteId != 0 AND exists(SELECT 1 FROM sales_quote WHERE id=quoteId AND customer_id=customerId AND is_active=1)) THEN
		
	ELSE
   		SELECT COALESCE((MAX(id) + 1),1) INTO quoteId FROM sales_quote
		INSERT INTO sales_quote(id, customer_id, is_active, website_id, total_items, total_items_qty, grand_total, created_at, updated_at)
		--VALUES(quoteId, customerId, 1, websiteId, quantity, quantity, 0, NOW(),NOW());
		SELECT quoteId, customerId, 1, websiteId, quantity, quantity, COALESCE(special_price, price), NOW(), NOW() FROM products_price WHERE product_id = productId;
	END IF;

END 