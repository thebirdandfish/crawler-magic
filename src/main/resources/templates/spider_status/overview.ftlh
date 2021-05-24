<html lang="en">
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->
<div id="wrapper" class="toggled">
    <p>Please allow up to 60 seconds after updating rule and spider statuses.</p>
    <p>Refresh this page anytime to view updates.</p>

    <hr>
    <h1>Scraper Overview (Local)</h1>
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
                <#list localRuleSpiderDaoMap as domain, localRuleSpiderDao>
                    <tr>
                        <td>
                            ${(domain)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.uuid)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.ipAddress)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.port?long?c)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.spiderStatus)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.leftPageCount)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.totalPageCount)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.successPageCount)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.errorPageCount)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.errorPagesListJson)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.thread)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.startTime?date)!}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.pagePerSecond)!''}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.lastInit?date)!}
                        </td>
                        <td>
                            ${(localRuleSpiderDao.lastStop?date)!}
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <hr>
    <h1>Scrapers (Global)</h1>
    <p>Local machine: ${(ipAddress)!''}:${(port?long?c)!''}</p>
    <div id="page-content-wrapper">
        <div class="container row clearfix col-md-12">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>
                        UUID
                    </th>
                    <th>
                        Domain
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
                            ${(spiderEntity.uuid)!''}
                        </td>
                        <td>
                            ${(spiderEntity.domain)!''}
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