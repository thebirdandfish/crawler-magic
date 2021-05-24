<html lang="en">
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->
<div id="wrapper" class="toggled">
    <p>Please allow up to 60 seconds after updating rule and spider statuses.</p>
    <p>Refresh this page anytime to view updates.</p>

    <hr>
    <h1>Scraper Overview (All)</h1>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>
                        Domain
                    </th>
                    <th>
                        Scraper UUID
                    </th>
                    <th>
                        Scraper IP Address
                    </th>
                    <th>
                        Scraper Port
                    </th>
                    <th>
                        Scraper Status
                    </th>
                    <th>
                        Left Page Count
                    </th>
                    <th>
                        Total Page Count
                    </th>
                    <th>
                        successPageCount
                    </th>
                    <th>
                        errorPageCount
                    </th>
                    <th>
                        errorPagesListJson
                    </th>
                    <th>
                        thread
                    </th>
                    <th>
                        startTime
                    </th>
                    <th>
                        pagePerSecond
                    </th>
                    <th>
                        lastInit
                    </th>
                    <th>
                        lastStop
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list ruleSpiderDaoMap as domain, ruleSpiderDao>
                    <tr>
                        <td>
                            ${(domain)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.uuid)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.ipAddress)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.port?long?c)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.spiderStatus)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.leftPageCount)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.totalPageCount)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.successPageCount)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.errorPageCount)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.errorPagesListJson)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.thread)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.startTime)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.pagePerSecond)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.lastInit)!''}
                        </td>
                        <td>
                            ${(ruleSpiderDao.lastStop)!''}
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <hr>
    <h1>Scrapers Running in Local</h1>
    <p>Local machine: ${(ipAddress)!''}:${(port?long?c)!''}</p>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Domain
                    </th>
                    <th>
                        UUID
                    </th>
                    <th>
                        Status
                    </th>
                    <th>
                        lastInit
                    </th>
                    <th>
                        lastStop
                    </th>
                    <th>
                        createTime
                    </th>
                    <th>
                        updateTime
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list spiderEntities as spiderEntity>
                    <tr>
                        <td>
                            ${(spiderEntity.id)!''}
                        </td>
                        <td>
                            ${(spiderEntity.domain)!''}
                        </td>
                        <td>
                            ${(spiderEntity.uuid)!''}
                        </td>
                        <td>
                            ${(spiderEntity.spiderStatus)!''}
                        </td>
                        <td>
                            ${(spiderEntity.lastInit)!''}
                        </td>
                        <td>
                            ${(spiderEntity.lastStop)!''}
                        </td>
                        <td>
                            ${(spiderEntity.createTime)!''}
                        </td>
                        <td>
                            ${(spiderEntity.updateTime)!''}
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</div>
<#--</div>-->
</body>
</html>