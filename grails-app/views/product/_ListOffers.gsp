<div id="offerList">
    <g:if test = "${offers != null}"> 
        <g:set var="entityName" value='Offer' />
        <div id="list-offers" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <table style="width:100%">
                <thead>
                    <tr>
                        <g:sortableColumn property="product" title='Product' />
                        <g:sortableColumn property="supplier" title='Supplier' />
                        <g:sortableColumn property="volume" title='Volume' />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${offers}" status="i" var="od">
                        <tr class="${ (i % 2) == 0 ? 'even': 'odd'}">
                            <td><g:link class="edit" action="edit" resource="offer" id="${od?.id}"> ${od.product?.encodeAsHTML()}</g:link></td>
                            <td>${od.supplier}
                            <td>[${od.volume}]</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${offersCount ?: 0}" />
            </div>
        </div>
    </g:if>
</div>
