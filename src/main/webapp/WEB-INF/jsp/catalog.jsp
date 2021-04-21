<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Catalog</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
	<br/>
	<form:label path="brands">Brand:</form:label>
	<form:select path="brands" id="brands">
	    <form:option value="-1">--NO BRANDS--</form:option>
	</form:select>
	<form:label path="articles">Article:</form:label>
	<form:select path="articles" id="articles">
	    <form:option value="-1">--NO ARTICLES--</form:option>
	</form:select>
	
	<p>
		Selected article: <span id="article"></span>
	</p>
</body>

<script type="text/javascript">

function populateBrands(){
	console.log("brands");
	$.get("/brands", function( data ) {
		console.log(data);
		var html = '<option value="-1">--SELECT BRAND--</option>';
		data.forEach(element => {
			html += ('<option value="'+element.id+'">'+element.name+'</option>');
		});
		$("#brands").html( html );
	});
}

function populateArticles(){
	var brand = $("#brands option:selected");
	console.log("articles");
	if(brand.val() != "-1"){
		$.get("/articles?brand="+brand.val(), function( data ) {
			console.log(data);
			var html = '<option value="-1">--SELECT ARTICLE--</option>';
			data.forEach(element => {
				html += ('<option value="'+element.id+'">'+element.name+'</option>');
			});
			$("#articles").html( html );
		});
	}
}

function showArticle(){
	var article = $("#articles option:selected");
	console.log("article");
	if(article.val() != "-1"){
		$.get("/article/"+article.val(), function( data ) {
			console.log(data);
			$("#article").html( data.name );
		});
	}
}

$("#brands").change(populateArticles);
$("#articles").change(showArticle);
populateBrands();

</script>
</html>