
#############################################

#############################################

-optimizationpasses 5


-dontusemixedcaseclassnames


-dontskipnonpubliclibraryclasses


-verbose


-dontskipnonpubliclibraryclassmembers


-dontpreverify


-keepattributes *Annotation*,InnerClasses


-keepattributes Signature


-keepattributes SourceFile,LineNumberTable


-optimizations !code/simplification/cast,!field/*,!class/merging/*


#############################################
#
#
#############################################


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View




-keep class android.support.** {*;}


-keep class com.google.gson.** {*;}

-dontobfuscate
-dontoptimize


-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**


-keep class **.R$* {*;}


-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}


-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}



# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn com.google.common.collect.ArrayListMultimap
-dontwarn com.google.common.collect.Multimap
-dontwarn java.awt.Color
-dontwarn java.awt.Font
-dontwarn java.awt.Point
-dontwarn java.awt.Rectangle
-dontwarn javax.money.CurrencyUnit
-dontwarn javax.money.Monetary
-dontwarn javax.ws.rs.Consumes
-dontwarn javax.ws.rs.Produces
-dontwarn javax.ws.rs.core.Response
-dontwarn javax.ws.rs.core.StreamingOutput
-dontwarn javax.ws.rs.ext.MessageBodyReader
-dontwarn javax.ws.rs.ext.MessageBodyWriter
-dontwarn javax.ws.rs.ext.Provider
-dontwarn org.glassfish.jersey.internal.spi.AutoDiscoverable
-dontwarn org.javamoney.moneta.Money
-dontwarn org.joda.time.DateTime
-dontwarn org.joda.time.DateTimeZone
-dontwarn org.joda.time.Duration
-dontwarn org.joda.time.Instant
-dontwarn org.joda.time.LocalDate
-dontwarn org.joda.time.LocalDateTime
-dontwarn org.joda.time.LocalTime
-dontwarn org.joda.time.Period
-dontwarn org.joda.time.ReadablePartial
-dontwarn org.joda.time.format.DateTimeFormat
-dontwarn org.joda.time.format.DateTimeFormatter
-dontwarn springfox.documentation.spring.web.json.Json







