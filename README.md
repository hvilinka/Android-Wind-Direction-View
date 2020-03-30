![header](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/Wind-Direction-View.png)


* [Overview](#overview)
* [Setup](#setup)
* [Features](#features)
  * [Directions](#directions)
  * [Grid](#grid)
    * [Text](#text)
    * [Lines](#lines)
  * [Petal](#petal)
    * [Top Style](#top_style)
    * [Bottom Style](#bottom_style)
  * [Margin](#margin)
  * [Bottom Radius](#bottom_radius)

<a name="overview"/>

# Overview

<a name="setup"/>

# Setup

build.gradle 

```grovy
dependencies {
    ...
    implementation 'com.radiance.winddirections:winddirections:1.0.0'
}
```

To display a WindDirections in an activity, add a petal to the activity's layout XML file:

```xml
  <com.radiance.winddirections.WindDirections
      android:id="@+id/wd_id"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:layout_constraintTop_toTopOf="parent"
      app:layout_constraintLeft_toLeftOf="parent"
      app:directionsCount="eight"
      
      app:grid_text_size="18dp"
      app:grid_text_color="#000000"
      app:grid_line_size="1dp"
      app:grid_line_color="#000000"
      
      app:petal_color="@color/colorAccent"
      app:petal_border_color="#000000"  
      app:petal_border_size="2dp"   
      app:petal_margin="2dp"        
      app:petal_top_style="sector"     
      app:petal_bottom_style="sector"     
      app:petal_bottom_radius="4dp" />
```

To set wind power:

```kotlin
// 1. Create WindPower object. WindPower contains wind power depending on the direction.
val windPower = WindDirections.WindPower().apply {N = 400
                                                  S = 300
                                                  E = 200
                                                  W = 500}
// 2. Find windDirection view
val windDirections = findViewById<WindDirections>(R.id.wd_id)
// 3. Set windPower property
windDirections.windPower = windPower
```

<a name="features"/>

# Features

<a name="directions"/>

## Directions

| Value         | Sample        | XML property | Kotlin property |
| ------------- |:-------------:|:-------------:|:-------------:|
| eight         | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/eight.jpg) | app:directionsCount="eight" | wdView.angle = WindDirections.Angle.Eight |
| sixteen       | ![sixteen](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/sixteen.jpg) | app:directionsCount="sixteen" | wdView.angle = WindDirections.AngleSixteen |

<a name="grid"/>

## Grid

<a name="text"/>

### Text

 | Value         | Sample        | XML property  | Kotlin property |
 | ------------- |:-------------:|:-------------:|:---------------:|
 |size     | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/text_size.gif) | app:grid_text_size="18dp" | wdView.gridTextSize = 10.0 |
 | color    | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/text_color.gif) | app:grid_text_color="#000000" | wdView.gridTextColor = color |


<a name="lines"/>

### Lines

 | Value         | Sample        | XML property  | Kotlin property |
 | ------------- |:-------------:|:-------------:|:---------------:|
 |size     | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/grid_size.gif) | app:grid_line_size="1dp" | wdView.gridLineSize = 3.0 |
 | color    | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/grid_color.gif) | app:grid_line_color="#000000" | wdView.gridLineColor = color |

<a name="petal"/>

## Petal

 | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | color | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_color.gif) | app:petal_color="@color/colorAccent" | wdView.petalColor = color |
 | border size | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_border_size.gif) | app:petal_border_size="2dp" | wdView.petalBorder = 3.0 |
  | border color | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_border_color.gif) | app:petal_border_color="#000000" | wdView.petalBorderColor = color |
  
<a name="top_style"/>  
  
### Top Style

 | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | sector | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/pot_style_sector.jpg) | app:petal_top_style="sector" | wdView.topStyle = Petal.TopStyle.Sector |
 | flat | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/top_style_flat.jpg) | app:petal_top_style="flat" | wdView.topStyle = Petal.TopStyle.Flat |
 
 <a name="bottom_style"/> 
 
 ### Bottom Style

 | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | sector | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/bottom_style_sector.jpg) | app:petal_bottom_style="sector" | wdView.bottomStyle = Petal.BottomStyle.Sector |
 | flat | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/bottom_style_flat.jpg) | app:petal_bottom_style="flat" | wdView.bottomStyle = Petal.BottomStyle.Flat |
 
 <a name="margin"/> 
 
 ## Margin
 
 | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | margin | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/margin.gif) | app:petal_margin="2dp" | wdView.petalMargin = 4.0 |
 
 <a name="bottom_radius"/> 
 
 ## Bottom Radius
 
  | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | radius | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/bottom.gif) | app:petal_bottom_radius="4dp" | wdView.bottomRadius = 4.0 |
