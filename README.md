# How to use the FeatureSetter
Double click RunFeatureSetter.bat to start the service; this will open a CMD window.

Use PostMan to call the APIs:
* http://localhost:8080/getFeatures/thing to check whether feature "thing" is enabled
* http://localhost:8080/setFeatures to enable a feature; set Body type to JSON e.g. {"featureName":"thing","enabled":"true"}

Close the CMD window when done.