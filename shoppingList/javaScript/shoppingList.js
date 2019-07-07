//商品类
function Product(id, name, price, unit) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.unit = unit;
}

//商品库
function ProductStore() {
	this.products = new Map();
}

ProductStore.prototype.addProduct = function(product) {
	this.products.set(product.id, product);
}

ProductStore.prototype.getProduct = function(id) {
	return this.products.get(id);
}

//促销类
function Sale(id, strategy) {
	this.id = id;
	this.strategy = strategy;
}

//促销库
function SaleStore() {
	this.sales = new Map();
}

SaleStore.prototype.addSale = function(sale) {
	this.sales.set(sale.id , sale);
}

SaleStore.prototype.getSale = function(id) {
	return this.sales.get(id);
}

//价格策略
var strategies = {
	"original": function(price, num) {
		return price * num;
	},
	"discount": function(price, num) {
		return price * num * 0.95;
	},
	"buyAndGetFree": function(price, num) {
		return price * (Math.floor(num / 3) * 2 + num % 3);
	}
};

var calculatePrice = function(strategy, price, num) {
	return strategies[strategy](price, num);
}

//商品定义
var productStore = new ProductStore();
productStore.addProduct(new Product("ITEM000001", "苹果", 5.5, "斤"));
productStore.addProduct(new Product("ITEM000002", "羽毛球", 1, "个"));
productStore.addProduct(new Product("ITEM000003", "可口可乐", 2, "瓶"));
productStore.addProduct(new Product("ITEM000004", "巧克力", 10, "盒"));

var saleStore = new SaleStore();
saleStore.addSale(new Sale("ITEM000001", ["buyAndGetFree"]));
saleStore.addSale(new Sale("ITEM000002", ["buyAndGetFree", "discount"]));
saleStore.addSale(new Sale("ITEM000004", ["discount"]));

//购物清单
var itemInput = ["ITEM000001", "ITEM000001", "ITEM000001", "ITEM000002-5", "ITEM000003-2", "ITEM000003", "ITEM000004"];
var shoppingList = parseShoppingList(itemInput);
var actualNumToBePaied = new Map();
var actualMoneyToBePaied = new Map();

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

// 计算每种商品实际需付款的数量和价格
for (var [id, num] of shoppingList) {
	var product = productStore.getProduct(id);
	var sale = saleStore.getSale(id);
	var actaulPrice = calculatePrice(getStrategy(sale), product.price, num);
	if(getStrategy(sale) == "buyAndGetFree") {
		num = Math.floor(num / 3) * 2 + num % 3;
	}
	actualNumToBePaied.set(id, num);
	actualMoneyToBePaied.set(id, actaulPrice);
}

//获取每种商品最终的优惠形式
function getStrategy(sale) {
	if (sale) {
		var strategy = sale.strategy;
		return strategy.indexOf("buyAndGetFree") >= 0 ? "buyAndGetFree" : strategy;
	}
	return "original";
}

//采购清单打印
var savedPriceSum = 0;
var actualPaidSum = 0;
var buyAndGetFreeText = "买二赠一商品: ";
var totalProductText = "所购物品如下：";
for (var [itemId, itemNum] of shoppingList) {
	var product = productStore.getProduct(itemId);
	var sale = saleStore.getSale(itemId);
	var paid = actualMoneyToBePaied.get(itemId).toFixed(2);
	var savedPrice = (product.price * itemNum - paid).toFixed(2);
	totalProductText = totalProductText + "<br>" + "<br>" + "名称： " + product.name + "， 数量: " + itemNum + product.unit + "， 单价： " + product.price + "（元）， 小计： " + paid + "（元）";
	if (getStrategy(sale) !== "original") {
		if (getStrategy(sale) == "buyAndGetFree") {
			var giventNum = itemNum - actualNumToBePaied.get(itemId);
			buyAndGetFreeText = buyAndGetFreeText + "<br>" + "<br>" + "名称： " + product.name + "数量： " + giventNum + product.unit;
		}
		totalProductText = totalProductText + "， 节省： " + savedPrice + "（元）";
	}

	actualPaidSum = actualPaidSum + parseFloat(paid); 
	savedPriceSum = savedPriceSum + parseFloat(savedPrice);
}

//显示数据：
function showShoppingList() {
	document.getElementById("productdetail").innerHTML = totalProductText;
	document.getElementById("discountdetail").innerHTML = buyAndGetFreeText;
	document.getElementById("total").innerHTML = "总价：" + actualPaidSum.toFixed(2) + "（元）" + "<br>" + "<br>" + "节省： " + savedPriceSum.toFixed(2) + "（元）";
}