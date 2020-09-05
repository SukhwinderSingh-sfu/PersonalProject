About project:-<br /> 
  >This project is android app, also launched on google play. Languages used to built this app - java, Html and javaScript.<br /> 
  
Tools used and API's integrated:-<br /> 
  >Android Studio, Firebase, Glide, Admob ads, Facebook Native ads.<br /> 
  
App Overview:-<br /> 
 > This is social media app, similar to Instagram and facebook.<br /> 
>  -upload memes, which will be posted on timeline of users following you.<br /> 
>  -like a meme, follow other users.<br /> 
>  -get a coin, when someone likes meme posted by you.<br /> 
>  -can change your username and profile picture any number of times.<br /> 
>  -profile activity- where you can see your profile picture, username, total coins earned, number of user's following you and also number of user's followed by you.<br /> 
  
 More technical details:-<br /> 
 > -Firebase authentication is used to keep record of the user's signing in.<br /> 
>  -when user open's the app, he reaches the main activity, where his authentication is checked and then moved to display activity or login activity accordingly.<br /> 
>  -display activity has four fragments :-<br /> 
>>      GalleryFragment- for user to upload memes to firebase database.<br /> 
>>      HomeFragment- Fragment containing the recyclerview to display memes on the tab.<br /> 
>>      SettingsFragment- change username and profile picture.<br /> 
>>      Profile- user can view his profile.<br /> 
  Users can move back and forth accross these fragments through navigation controller.<br /> 
>  -firebase realtime database is used to keep track of likes/unlikes on the meme, user followers and memes uploaded.<br /> 
>  -Memes are stored in the firebase in queue data structure using the time (upto nanoseconds) they were uploaded. <br /> 
>  -firebase cloud function is triggered everytime memes reaches the certain maximum limit in the bucket and deleting half of the memes, thus making the room for 
>   newer ones.<br /> 
   
New experiences gained:-<br /> 
>  -paging/caching- Without pre/postcaching activities usually loads the data currently on the screen. I used paging to preload the tabs recylcerviewer view will 
>  use as user scrolls down. Thus providing smooth experience for user's to scroll.<br /> 
>  -Integration of admob and facebook's API's.<br /> 
>  -Full stack experience and working with realtime data using firebase database and cloud functions.<br /> 
  
  link:-  https://play.google.com/store/apps/details?id=dhaliwal.production.memeking
  
  
  
  
