<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                爬虫管理
            </a>
        </li>
        <li>
            <a href="/ruleSpider/overview"><i class="fa fa-fw fa-list-alt"></i> 总览(本地)</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 开关 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/ruleControl/list">列表</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 爬虫 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/spider/local/list">本机爬虫</a></li>
                <li><a href="/spider/global/list">所有爬虫</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 抓取规则 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/rule/list">所有规则</a></li>
                <li><a href="/rule/index">新增规则</a></li>
                <li><a href="/rule/trial">试验规则</a></li>
            </ul>
        </li>

    </ul>
</nav>