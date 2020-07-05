# AndroidRecordVoiceDialog


![Image Result](https://im2.ezgif.com/tmp/ezgif-2-7103a21fa804.gif)

## Getting started
* In ``` build.gradle ``` (  Project Level  )

Add maven of jitback

 ```
allprojects { 
     repositories { 
        maven { url 'https://jitpack.io' }
      }
 }   
```
* In ``` build.gradle ``` (  app Level  )

Add library as dependency 
```
 implementation 'com.github.alex8530:AndroidRecordVoiceDialog:Tag'
```
and be sure you have material
```
 implementation 'com.google.android.material:material:1.0.0'
```
   

## How to use

* simple ! just in your kotlin code write this !

   ```java
   
  val dailog = RecordVoiceBottomSheetFragmentDialog()
  r.show(supportFragmentManager, "tag!")
   
   ```
   
* Listen events

   ```java
     dailog.recordListener= (object :OnRecordListener{
                override fun onCancel() {
                    Log.d(TAG, "onCancel")
                }

                override fun onFinish(recordTime: Long) {
                    Log.d(TAG, "onFinish : " + recordTime)

                }

                override fun onLessThanSecond() {
                    Log.d(TAG, "onLessThanSecond")
                }

                override fun onSend() {
                    Log.d(TAG, "onSend")
                }

                override fun onStartRecording() {
                    Log.d(TAG, "onStartRecording")
                }

            })
   
   ```
  * Enjoy!
  
  
   
