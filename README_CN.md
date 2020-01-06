# StatusView

Status view base on ViewFlipper.

## 下载

### 步骤1. 

添加以下配置到你项目根目录的`build.gradle`

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 步骤2. 

在你项目module中的`build.gradle`中添加依赖

```groovy
dependencies {
    implementation 'com.github.zeropercenthappy:StatusView:1.0.0'
}
```

## 使用

### 属性

```xml
<com.zeropercenthappy.statusview.StatusView
    android:id="@+id/statusView"                                 
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:emptyView="@layout/layout_empty"
    app:errorView="@layout/layout_error"
    app:loadingView="@layout/layout_loading">
</com.zeropercenthappy.statusview.StatusView>
```

| 属性        | 描述       | 类型             |
| ----------- | ---------- | ---------------- |
| emptyView   | 空数据视图 | Layout reference |
| errorView   | 出错视图   | Layout reference |
| loadingView | 加载中视图 | Layout reference |

因为`StatusView`是基于`ViewFlipper`的，所以你可以使用`inAnimation`和`outAnimation`来实现视图切换时的动画效果

| 属性         | 类型                |
| ------------ | ------------------- |
| inAnimation  | Animation reference |
| outAnimation | Animation reference |

### 方法

| Method                                                 | Describe                                                     |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| getLoadingView()                                       | 获取加载中视图的View，如果未设置，则返回null                 |
| getEmptyView()                                         | 同上                                                         |
| getErrorView()                                         | 同上                                                         |
| addLoadingView(view: View)                             | 添加加载中视图View<br>如果在此之前已添加过，则旧视图将被移除 |
| addEmptyView(view: View)                               | 同上                                                         |
| addErroViewr(view: View)                               | 同上                                                         |
| addLoadingViewCallback(statusCallback: StatusCallback) | 添加加载中视图的回调监听<br>可以在这里做一些该视图需要的初始化操作，或开启、结束该视图的特殊动画 |
| addEmptyViewCallback(statusCallback: StatusCallback)   | 同上                                                         |
| addErrorViewCallback(statusCallback: StatusCallback)   | 同上                                                         |
| showNormal()                                           | 展示正常状态视图                                             |
| showLoading()                                          | 同上                                                         |
| showEmpty()                                            | 同上                                                         |
| showError()                                            | 同上                                                         |



