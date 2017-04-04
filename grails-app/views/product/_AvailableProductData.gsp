<div id="divToUpdate">
    <table>
        <thead>
            <tr>
                <g:sortableColumn property="Id" title='Id' />
                <g:sortableColumn property="supplier" title='Supplier' />
                <g:sortableColumn property="product" title='Product' />
                <g:sortableColumn property="product" title='Volume' />
            </tr>
        </thead>
        <tbody>
            <g:each in="${products}" status="i" var="pb"> 
                <tr  class="${ (i % 2) == 0 ? 'even': 'odd'}">
                    <td><g:link action="edit" id="${pb.id}">${pb.id}</g:link></td>
                    <td>${pb.supplier}</td>
                    <td>${pb.name}</td>
                    <td><div id="${pb.id}" class="offers" >${pb.volume}</div></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>