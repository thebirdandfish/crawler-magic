<html lang="en">
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->
<div id="wrapper" class="toggled">
    <p>Please allow up to 60 seconds after updating rule and spider statuses.</p>
    <p>Refresh this page anytime to view updates.</p>

    <hr>
    <h1>Extracted Logs - ${(specification)!''} - ${(domain)!''}</h1>
    <p>The followings are the latest extracted data.</p>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>
                        Rule ID
                    </th>
                    <th>
                        Entity ID
                    </th>
                    <th>
                        .toString()
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list entityList as entity>
                    <tr>
                        <td>
                            ${(entity.ruleId?long?c)!''}
                        </td>
                        <td>
                            ${(entity.id?long?c)!''}
                        </td>
                        <td>
                            ${entity.toString()}
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <a target="_blank" href="/scraper/status/monitor">
        <h2>
            Scraper Overview
        </h2>
    </a>

    <a target="_blank" href="/rule/book/list">
        <h2>
            Rule Overview
        </h2>
    </a>

</div>
<#--</div>-->
</body>
</html>