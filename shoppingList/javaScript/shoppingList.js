function shopping() {
	 class Product {
		constructor(id, name, price, unit) {
			this.id = id;
			this.name = name;
			this.price = price;
			this.unit = unit;
		}
	}

	class ProductStore {
		constructor() {
			this.discountItems = [];
			this.buyTwoAndGiveOneItems = [];
			this.products = new Map();
			this.DISCOUNT = 0.95;
		}

		getProduct(id) {
			return this.products.get(id);
		}

		addDiscountItem(id) {
			this.discountItems.push(id);
		}

		addBuyTwoAndGiveOneItem(id) {
			this.buyTwoAndGiveOneItems.push(id)
		}

		addProduct(product) {
			this.products.set(product.id, product);
		}

		isDiscountItem(id) {
			return this.discountItems.indexOf(id) != -1;
		}

		isBuyTwoAndGiveOneItem(id) {
			return this.buyTwoAndGiveOneItems.indexOf(id) != -1;
		}
	}

	var productStore = new ProductStore();
	productStore.addDiscountItem("ITEM000001");
	productStore.addDiscountItem("ITEM000002");
	productStore.addBuyTwoAndGiveOneItem("ITEM000001");
	productStore.addBuyTwoAndGiveOneItem("ITEM000004");

	productStore.addProduct(new Product("ITEM000001", "苹果", 5.5, "斤"));
	productStore.addProduct(new Product("ITEM000002", "羽毛球", 1, "个"));
	productStore.addProduct(new Product("ITEM000003", "可口可乐", 2, "瓶"));
	productStore.addProduct(new Product("ITEM000004", "巧克力", 10, "盒"));

	//购物清单
	var itemInput = ["ITEM000001", "ITEM000001", "ITEM000001", "ITEM000002-5", "ITEM000003-2", "ITEM000003", "ITEM000004"];
	
	//统计购物清单中商品的种类和数量
	function parseShoppingList(itemInput) {
		var list = new Map();
		for (var i = 0; i < itemInput.length; i++) {
		    var item = itemInput[i];
		    var pos = item.search("-");
		    var id = (pos != -1) ? item.substr(0, pos) : item;
		    var num = (pos != -1) ? parseInt(item.substr(pos + 1)) : 1;
		    list.has(id) ? list.set(id, list.get(id) + num) : list.set(id, num);
		}
		return list;
	}

	var shoppingList = parseShoppingList(itemInput);
	var actualNumToBePaied = new Map();
	var actualMoneyToBePaied = new Map();

	//计算每种商品实际需付款的数量和价格
	for (var [id, num] of shoppingList) {
		var price = productStore.getProduct(id).price;
		var actualNum = num;
		var actaulPrice = actualNum * price;
		if (productStore.isBuyTwoAndGiveOneItem(id)) {
			actualNum = Math.floor(num / 3) * 2 + num % 3;
			actaulPrice = actualNum * price;
		} else if (productStore.isDiscountItem(id)) {
			actaulPrice = actaulPrice * productStore.DISCOUNT;
		}
		actualNumToBePaied.set(id, actualNum);
		actualMoneyToBePaied.set(id, actaulPrice);
	}

	//采购清单打印
	var savedPriceSum = 0;
	var actualPaidSum = 0;
	var discountText = "买二赠一商品: ";
	var totalProductText = "所购物品如下：";
	for (var [itemId, itemNum] of shoppingList) {
		var product = productStore.getProduct(itemId);
		var paid = actualMoneyToBePaied.get(itemId);
		var savedPrice = product.price * itemNum - paid;
		totalProductText = totalProductText + "<br>" + "<br>" + "名称： " + product.name + "， 数量: " + itemNum + product.unit + "， 单价： " + product.price + "（元）， 小计： " + paid + "（元）";
		if (productStore.isDiscountItem(itemId) || productStore.isDiscountItem(itemId)) {
			if (productStore.isDiscountItem(itemId)) {
				var discountNum = itemNum - actualNumToBePaied.get(itemId);
				discountText = discountText + "<br>" + "<br>" + "名称： " + product.name + "数量： " + discountNum + product.unit;
			}
			totalProductText = totalProductText + "， 节省： " + savedPrice + "（元）";
		}

		actualPaidSum = actualPaidSum + paid; 
		savedPriceSum = savedPriceSum + savedPrice;
	}

	//显示数据：
	document.getElementById("productdetail").innerHTML = totalProductText;
	document.getElementById("discountdetail").innerHTML = discountText;
	document.getElementById("total").innerHTML = "总价：" + actualPaidSum + "（元）" + "<br>" + "<br>" + "节省： " + savedPriceSum.toFixed(2) + "（元）";

}