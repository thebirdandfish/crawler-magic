<html>
<#include "../common/header.ftlh">
<body>
<#--<div id="wrapper" class="toggled">-->

<#include "../common/nav.ftlh">

<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>
                            规则Id
                        </th>
                        <th>
                            规则状态
                        </th>
                        <th>
                            爬虫状态
                        </th>
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
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#--</div>-->
</body>
</html>