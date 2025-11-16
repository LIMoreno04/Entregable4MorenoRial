Write-Host "Building..."
mvn -DskipTests=false clean package

Write-Host "Stopping previous processes..."
Get-Process -Name java -ErrorAction SilentlyContinue | Where-Object {$_.Path -like "*miplaylist*"} | Stop-Process -Force

Write-Host "Copy artifact..."
New-Item -ItemType Directory -Force -Path "C:\miplaylist"
Copy-Item -Path ".\target\*.jar" -Destination "C:\miplaylist\miplaylist.jar" -Force

Write-Host "Starting app..."
Start-Process -FilePath "java" -ArgumentList "-jar C:\miplaylist\miplaylist.jar" -WindowStyle Hidden
Write-Host "Deploy finished."
