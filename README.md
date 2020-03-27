![header](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/Wind-Direction-View.png)

# Overview

# Setup

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
      app:petal_bottom_size="sector"     
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

# Features
## Directions

| Value         | Sample        | XML property | Kotlin property |
| ------------- |:-------------:|:-------------:|:-------------:|
| eight         | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/eight.jpg) | app:directionsCount="eight" | wdView.angle = WindDirections.Angle.Eight |
| sixteen       | ![sixteen](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/sixteen.jpg) | app:directionsCount="sixteen" | wdView.angle = WindDirections.AngleSixteen |

## Grid
### Text

 | Value         | Sample        | XML property  | Kotlin property |
 | ------------- |:-------------:|:-------------:|:---------------:|
 |size     | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/text_size.gif) | app:grid_text_size="18dp" | wdView.gridTextSize = 10.0 |
 | color    | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/text_color.gif) | app:grid_text_color="#000000" | wdView.gridTextColor = color |

### Lines

      
 | Value         | Sample        | XML property  | Kotlin property |
 | ------------- |:-------------:|:-------------:|:---------------:|
 |size     | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/grid_size.gif) | app:grid_line_size="1dp" | wdView.gridLineSize = 3.0 |
 | color    | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/grid_color.gif) | app:grid_line_color="#000000" | wdView.gridLineColor = color |

## Petal

 | Value         | Sample        | XML property  | Kotlin property |
 | :-----------: |:-------------:|:-------------:|:---------------:|
 | color | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_color.gif) | app:petal_color="@color/colorAccent" | wdView.petalColor = color |
 | border size | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_border_size.gif) | app:petal_border_size="2dp" | wdView.petalBorder = 3.0 |
  | border color | ![eight](https://github.com/KessoPavel/images/blob/master/Wind-Direction-View/petal_border_color.gif) | app:petal_border_color="#000000" | wdView.petalBorderColor = color |
  
### Style
