<#import "parts/common.ftl" as c>

<@c.page>
<div>
    <#--<form action="upload.php" method="post" enctype="multipart/form-data">-->
        <#--Select image to upload:-->
        <#--<input type="file" name="fileToUpload" id="fileToUpload">-->
        <#--<input type="submit" value="Upload Image" name="submit">-->
    <#--</form>-->
    <div>
        <form method="post" action="/addDocument" enctype="multipart/form-data">
            <input type="file" name="file"/><br/>
            <button type="submit">Add document url</button>
        </form>
    </div>
    <div>Document list</div>
    <#list documents as document>
        <div>
            <form method="get" action="/showDocument">
                <input name="id" value="${document.id}"/>
                <span>${document.name}</span>
                <button type="submit">Show</button>
            </form>
        </div>
        <#else>
        No documents
    </#list>
</div>
</@c.page>