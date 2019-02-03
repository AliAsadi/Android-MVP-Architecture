# Android MVP Architecture

### A basic sample android application to understand MVP in a very simple way. Just clone, build, run and understand MVP.

![](https://i.imgur.com/VLyoGuk.png)


# Project Structure

![](https://i.imgur.com/lNMQMdx.png)

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding Presenters.
4. **utils**: Utility classes.

#### Key points
* Using base abstract classes to support MVP methodology while
  avoiding Activity leaks by releasing the view when it's no longer needed 
  ,BasePresenter repsonsable to destroy the view onDestroy().
  
#### Used libraries:
1. [RoomDatabase](https://developer.android.com/topic/libraries/architecture/room)
2. [ButterKnife](http://jakewharton.github.io/butterknife/)
3. [Retrofit](https://github.com/square/retrofit)
4. [PowerPreference](https://github.com/AliEsaAssadi/Android-Power-Preference)
5. [Gson](https://github.com/google/gson)
6. [Picasso](https://github.com/square/picasso)
7. [Mockito](https://github.com/mockito/mockito)

--------------------------------------------------------------------------------------------

**Feel free to submit any type of issues and suggestions for improving the coding standard**

**Happy Coding!!!** ![](https://i.imgur.com/rneCZCN.png)

--------------------------------------------------------------------------------------------

### License
```
   Copyright (C) 2018 Ali Esa Assadi
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

### Contributing to Android MVP Architecture
Just make pull request. You are in!
