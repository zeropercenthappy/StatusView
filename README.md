# StatusView [简体中文](https://github.com/zeropercenthappy/StatusView/blob/master/README_CN.md)

Status view base on ViewFlipper.

## Download

### Step 1. 

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. 

Add the dependency in your module `build.gradle`.

```groovy
dependencies {
    implementation 'com.github.zeropercenthappy:StatusView:1.0.0'
}
```

## Usage

### Attributes

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

| attribute   | describe            | type             |
| ----------- | ------------------- | ---------------- |
| emptyView   | Empty view layout   | Layout reference |
| errorView   | Error view layout   | Layout reference |
| loadingView | Loading view layout | Layout reference |

Consider it's base on `ViewFlipper`, so you can use `inAnimation` and `outAnimation` if you need a switcher animation.

| attribute    | type                |
| ------------ | ------------------- |
| inAnimation  | Animation reference |
| outAnimation | Animation reference |

### Methods

| Method                                                 | Describe                                                     |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| getLoadingView()                                       | Get loading status view or null if it doesn't exist.         |
| getEmptyView()                                         | Same as above.                                               |
| getErrorView()                                         | Same as above.                                               |
| addLoadingView(view: View)                             | Add a loading status view.<br>It will remove old loading status view if it exist. |
| addEmptyView(view: View)                               | Same as above.                                               |
| addErroViewr(view: View)                               | Same as above.                                               |
| addLoadingViewCallback(statusCallback: StatusCallback) | Add a listener to loading status view.<br>You can do some initialize work, start or end special animation here. |
| addEmptyViewCallback(statusCallback: StatusCallback)   | Same as above.                                               |
| addErrorViewCallback(statusCallback: StatusCallback)   | Same as above.                                               |
| showNormal()                                           | Show normal status view.                                     |
| showLoading()                                          | Same as above.                                               |
| showEmpty()                                            | Same as above.                                               |
| showError()                                            | Same as above.                                               |



