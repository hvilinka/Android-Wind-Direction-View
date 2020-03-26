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

## Grid text size
