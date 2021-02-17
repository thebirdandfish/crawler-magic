# port  
|port|app|
|----|----|
|8111|crawler|



# Overview

```mermaid
graph LR

SpiderDen==spiderStatus==>SpiderQueryService
SpiderDbService==spiderEntity==>SpiderQueryService
SpiderQueryService--sync-->SpiderQueryService
SpiderQueryService==spiderEntity==>RuleSpiderUpdater

RuleControlDb==RuleControl==>RuleSpiderUpdater

RuleSpiderUpdater--scheduled-->RuleSpiderUpdater

Observable--notify/update-->RuleSpiderExhibit
RuleSpiderExhibit==Dao==>Controller
Observable--notify/update-->SpiderRunner

SpiderRunner-->SpiderCreator
SpiderCreator--create-->Spider((Spider))
SpiderRunner--run/stop-->Spider((Spider))


```
## Rule
```mermaid
graph TD
    subgraph rule classification
    BookRuleDb-->|allId / allDomain|AllRuleDb
    StoreRuleDb-->|allId / allDomain|AllRuleDb
    OtherRuleDb(...)-->AllRuleDb
    AllRuleDb---|all Id / allDomain|Others

    end
​```
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTYzMjYyMjgxMSwyMjYwMDYwODgsMTE1MT
Q4NDY2NCwtMjAyOTI4Njc0MCwxMTQ2MDMxNjAsMTY1ODAwNzAw
NiwtMTIxODE2Mzk3NiwtMzE0ODM4MDA0LDE2MjA2NTA0NjBdfQ
==
-->