function shopping() {
	function Product(id, name, price, unit) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.unit = unit;
	}
	//购物码list
	var itemInput = ["ITEM000001", "ITEM000001", "ITEM000001", "ITEM000002-5", "ITEM000003-2", "ITEM000003", "ITEM000004"];

	//商品优惠码list
	var discountItem = ["ITEM000001", "ITEM000002"];
	var buyTwoAndGiveOneItem = ["ITEM000001", "ITEM0000021"];

	//商品类
	var productStore = new Map();
	productStore.set("ITEM000001", new Product("ITEM000001", "苹果", 5.5, "斤"));
	productStore.set("ITEM000002", new Product("ITEM000002", "羽毛球", 1, "个"));
	productStore.set("ITEM000003", new Product("ITEM000003", "可口可乐", 2, "瓶"));
	productStore.set("ITEM000004", new Product("ITEM000004", "巧克力", 10, "盒"));

	var shoppingList = new Map();
	var discountInList = new Map();
	var discountText = "买二赠一商品: ";
	var actualPaidSum = 0;
	var discountSum = 0;
	var DISCOUNT = 0.95;

	//购物列表
	for (var i = 0; i < itemInput.length; i++) {
	    var item = itemInput[i];
	    var v = item.search("-");
	    if (v == -1) {
	        shoppingList.has(item) ? shoppingList.set(item, shoppingList.get(item) + 1) : shoppingList.set(item, 1);
	    } else {
	    	var itemId = item.substr(0, v);
	    	var itemNum = item.substr(v+1);
	        shoppingList.has(itemId) ? shoppingList.set(itemId, shoppingList.get(item) + parseInt(itemNum)) : shoppingList.set(itemId, parseInt(itemNum));
	    }
	}

	//返回满二赠一所需付款的实际数量
	function actualPaidNum(productId, productNum) {
		return buyTwoAndGiveOneItem.indexOf(productId) == -1 ? productNum : Math.floor(productNum / 3) * 2 + productNum % 3;
	}

	//实际付款价格
	function actualPaidMoney(productId, productNum, price) {
		if (buyTwoAndGiveOneItem.indexOf(productId) != -1) {
			return actualPaidNum(productId, productNum) * price;
		} else if(discountItem.indexOf(productId) != -1) {
			return parseFloat((productNum * price * DISCOUNT).toFixed(2));
		}
		return productNum * price;
	}

	//采购清单打印
	var totalPrice = 0;
	var totalProductText = "所购物品如下：";
	for (var [itemId, itemNum] of shoppingList) {
		var product = productStore.get(itemId);
		var paid = actualPaidMoney(itemId, itemNum, product.price);
		totalProductText = totalProductText + "<br>" + "<br>" + "名称： " + product.name + "， 数量: " + itemNum + product.unit + "， 单价： " + product.price + "（元）， 小计： " + paid + "（元）";
		if (buyTwoAndGiveOneItem.indexOf(itemId) != -1 || discountItem.indexOf(itemId) != -1) {
			if (buyTwoAndGiveOneItem.indexOf(itemId) != -1) {
				var discountNum = itemNum - actualPaidNum(itemId, itemNum);
				discountText = discountText + "<br>" + "<br>" + "名称： " + product.name + "数量： " + discountNum + product.unit;
			}
			totalProductText = totalProductText + "， 节省： " + (product.price * itemNum - paid) + "（元）";
		}

		actualPaidSum = actualPaidSum + paid; 
		totalPrice = totalPrice + product.price * itemNum;
	}

	// 显示对象中的数据：
	document.getElementById("productdetail").innerHTML = totalProductText;
	document.getElementById("discountdetail").innerHTML = discountText;
	document.getElementById("total").innerHTML = "总价：" + actualPaidSum + "（元）" + "<br>" + "<br>" + "节省： " + (totalPrice - actualPaidSum).toFixed(2) + "（元）";
}