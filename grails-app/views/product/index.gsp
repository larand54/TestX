<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </style>
    <style>
        .offers {
        color: #ff0000
        }
        </</style>
    <script type="text/javascript">
        function availableProducts(){
            $.ajax({
                url:'${g.createLink( controller:'product', action:'availableProducts' )}',
                    data: [supplier],
                    type: 'get'
            }).success( function ( data ) { $( '#divToUpdate' ).html( data ); });
        }


        $( document ).ready( function() {
            $( '.offers' ).click( function ( event ){
                $.ajax({
                    url: '${g.createLink( controller:'product', action:'listOffers' )}',
                        data: {id:this.id},
                        type: 'get'
                }).success( function ( data ) { $( '#offerList' ).html( data );     });
            });
        });

    </script>    
</head>
<body>
    <a href="#list-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div id="selectMill">
            Select mill:
            <g:select name="supplier" from="${millList}" value="" onchange="availableProducts(supplier)" noSelection = "${['':'All']}" />
        </div>
        <g:render template="AvailableProductData" model="[product:product]"/>
        <div class="pagination">
            <g:paginate total="${productCount ?: 0}" />
        </div>
        <g:render template="ListOffers" model="[offer:offer]"/>
    </div>
</body>
</html>


