<html lang="en">
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->
<div id="wrapper" class="toggled">
    <p>The page is downloaded from url:</p>
    <p>${(url)!''}</p>

    <div class="form-group">
        <label for="htmlString">The downloaded page is transformed into html:</label>
        <textarea class="form-control rounded-0" id="htmlString" rows="10" cols="10">
            ${(html)!''}
        </textarea>
    </div>
    <div class="form-group">
        <label for="xpath">The Xpath used for parsing is:</label>
        <input type="text" class="form-control" id="xpath" name="xpath" value="${(xpath)!''}">
    </div>

    <h2>Evaluate Again</h2>
    <form action="/xpathEvaluator/download/parse" method="get">
        <div class="form-group">
            <label for="url">URL</label>
            <input type="url" class="form-control" id="url" name="url" value="${(url)!''}" placeholder="Enter URL">
        </div>
        <div class="form-group">
            <label for="xpath">Xpath</label>
            <input type="text" class="form-control" id="xpath" name="xpath" value="${(xpath)!''}" placeholder="Xpath">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

    <hr>
    <h1>The parsed result</h1>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <#if selectedList??>
                <#list selectedList as selected>
                    <p>
                        ${(selected)!''}
                    </p>
                </#list>
            <#--            <#elseif condition2>-->
            <#else>
                Error Happened. ${(exception)!''}
            </#if>
        </div>
    </div>
</div>
</body>
</html>