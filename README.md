# TooltipProgressBar
Android library for adding a progress bar with tooltips in your projects

<img src="/screenshots/ss.png" width="450" title="TooltipProgressBar Screenshot">

# Usage:

XML:
```
     <com.anujkumarsharma.tooltipprogressbar.TooltipProgressBar
        android:id="@+id/tool_tip_progress_bar"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:optionTopTooltipText="Your: 45%"
        app:optionBottomTooltipText="Gold: 80%"
        app:optionTopTooltipColor="@color/red"
        app:optionBottomTooltipColor="@color/golden"
        app:optionLeftText="0%"
        app:optionRightText="100%"
        app:optionProgressFillColor="@color/black"
        app:optionProgressFillSteps="45"
        app:optionBottomTooltipSteps="80"
        app:optionProgressTotalSteps="100"/>
```

# Download
[ ![Download](https://api.bintray.com/packages/androidville/PulsatingButton/pulsatingbutton/images/download.svg) ](https://bintray.com/beta/#/anujkumarsharma/TooltipProgressBar/tooltipprogressbar/)

For Maven:
```
<dependency>
	<groupId>com.anujkumarsharma.tooltipprogressbar</groupId>
	<artifactId>tooltipprogressbar</artifactId>
	<version>1.1.1</version>
	<type>pom</type>
</dependency>
```
For Gradle:
```
implementation 'com.anujkumarsharma.tooltipprogressbar:tooltipprogressbar:1.1.1'
```

# Developed By

Anuj Kumar Sharma

### License
```
   Copyright (C) 2019 Anuj Kumar Sharma
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to TooltipProgressBar

All pull requests are welcome.
