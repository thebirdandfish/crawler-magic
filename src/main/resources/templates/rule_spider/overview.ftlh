<html lang="en">
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->
<div id="wrapper" class="toggled">
    <p>Please allow up to 60 seconds after updating rule and spider statuses.</p>
    <p>Refresh this page anytime to view updates.</p>

    <hr>
    <h1>Rules and Scrapers</h1>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>
                        Rule Id
                    </th>
                    <th>
                        Rule Status
                    </th>
                    <th>
                        Scraper Status
                    </th>
                    <th colspan="2">Operation</th>
                    <th colspan="1">Scraping Logs</th>
                    <th colspan="1">Reset Duplication Check (Set)</th>
                </tr>
                </thead>
                <tbody>
                <#list ruleSpiderDaoMap as domain, ruleSpiderDao>
                    <tr>
                        <td>
                            ${domain}
                        </td>
                        <td>
                            ${ruleSpiderDao.ruleStatus}
                        </td>
                        <td>
                            ${ruleSpiderDao.spiderStatus}
                        </td>
                        <td>
                            <a target="_blank" href="/ruleControl/turnOn?domain=${domain}">Turn On</a>
                        </td>
                        <td>
                            <a target="_blank" href="/ruleControl/turnOff?domain=${domain}">Turn Off</a>
                        </td>
                        <td>
                            <a target="_blank" href="/extracted/book/logs?domain=${domain}">Logs</a>
                        </td>
                        <td>
                            <a target="_blank" href="/duplicationCheck/reset?domain=${domain}">Reset</a>
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

    <h2>Xpath Evaluator</h2>
    <form action="/xpathEvaluator/download/parse" method="get" target="_blank">
        <div class="form-group">
            <label for="url">URL</label>
            <input type="url" class="form-control" id="url" name="url" placeholder="Enter URL">
        </div>
        <div class="form-group">
            <label for="xpath">Xpath</label>
            <input type="text" class="form-control" id="xpath" name="xpath" placeholder="Xpath">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
<#--</div>-->
</body>
</html>