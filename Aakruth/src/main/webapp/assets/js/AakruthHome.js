var data1 = [];
var data2 = [];
function gd(year, month, day) {
	return new Date(year, month, day).getTime();
}
function getSale() {
	/* This JQUERY loads the options to the builder select */
	var data = [];
	$.ajax({
        url : "/Aakruth/sale/saleChart",
        success : function(responseJson) {
        	$.each(responseJson, function(index, saleData) {
    			var sale = [(new Date(saleData.date)).getTime(),saleData.amt];
    			data1.push(sale);
        	});
        	getPurchase();
        },
        error : function(response) {
			new PNotify(
					{
						title : 'Failed!',
						text : 'Error while loading Sale chart',
						type : 'error',
						styling : 'bootstrap3'
					});
		}
     });
	
	//return data1;
}
function getPurchase() {
	/* This JQUERY loads the options to the builder select */
	var data = []
	$.ajax({
        url : "/Aakruth/purchase/purchaseChart",
        success : function(responseJson) {
        	$.each(responseJson, function(index, purchaseData) {
    			var purchase = [(new Date(purchaseData.date)).getTime(),purchaseData.amt];
    			data2.push(purchase);
    		});
        	plotChart();
        },
        error : function(response) {
			new PNotify(
					{
						title : 'Failed!',
						text : 'Error while loading Sale chart',
						type : 'error',
						styling : 'bootstrap3'
					});
		}
     });
	
}
function plotChart() {
	$("#canvas_dahs").length && $.plot($("#canvas_dahs"), [data1,data2], {
		series : {
			lines : {
				show : false,
				fill : true
			},
			splines : {
				show : true,
				tension : 0.4,
				lineWidth : 1,
				fill : 0.4
			},
			points : {
				radius : 0,
				show : true
			},
			shadowSize : 2
		},
		grid : {
			verticalLines : true,
			hoverable : true,
			clickable : true,
			tickColor : "#d5d5d5",
			borderWidth : 1,
			color : '#fff'
		},
		colors : [ "rgba(38, 185, 154, 0.38)", "rgba(3, 88, 106, 0.38)" ],
		xaxis : {
			tickColor : "rgba(51, 51, 51, 0.06)",
			mode : "time",
			tickSize : [ 1, "day" ],
			// tickLength: 10,
			axisLabel : "Date",
			axisLabelUseCanvas : true,
			axisLabelFontSizePixels : 12,
			axisLabelFontFamily : 'Verdana, Arial',
			axisLabelPadding : 10
		},
		yaxis : {
			ticks : 8,
			tickColor : "rgba(51, 51, 51, 0.06)",
		},
		tooltip : true
	});
}
