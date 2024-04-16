# TitlePlugin
### 称号插件
### 支持前缀后缀分别开启
### 支持渐变颜色
### 支持经济插件(使用vault的插件)
### 支持点券插件(playerpoints)
## 指令+用法+权限
### 玩家:

权限:titleplugin.player

指令:

/tip shop ---打开称号商店

/tip ui ---打开个人称号仓库

### OP:

权限:titleplugin.op

指令:

/atip list ---展示所有称号

/atip create activity [称号名称] [称号描述] ---创建活动称号

/atip create coin [称号名称] [称号描述] [金币] ---创建金币称号

/atip create points [称号名称] [称号描述] [点券] ---创建点券称号

/atip create permission [称号名称] [称号描述] [权限] ---创建权限称号

/atip del [称号ID] ---删除称号

/atip setcoin [称号ID] [金币] ---设置称号购买所需金币

/atip setpoints [称号ID] [点券] ---设置称号购买所需点券

/atip setdescription [称号ID] [描述] ---编辑称号描述

/atip setpermission [称号ID] [权限] ---设置称号权限

/atip setcanbuy [称号ID] [true/false] ---设置称号能否购买

/atip setyouxiao [称号ID] [天数] ---设置称号购买有效期

/atip setjiezhi [称号ID] [天数] ---设置称号购买截止日期

/atip setjiezhi [称号ID] [null] ---删除称号购买截止日期

/atip add [玩家] [称号ID] ---向玩家添加称号",

/atip add [玩家] [称号ID] [天数] ---向玩家添加具有有效期的称号
/atip del [玩家] [称号ID] ---从玩家那里删除称号
## 渐变称号的创建
例子:/atip create activity &#FF00EE苏小林林林林&#00FFFF

这样子就可以创建一个渐变颜色的活动称号
## 开源遵循GPL-3.0协议