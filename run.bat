@echo off

echo Compiling the project...
call mvn clean compile

echo Running automated tests...
call mvn test

echo Opening report...
for /F "delims=" %%I in ('dir /b /o:d reports\Test-Report-*.html') do set latestReport=reports\%%I
if defined latestReport (
    start "" "%latestReport%"
) else (
    echo No reports were found.
)
REM start "" "reports\Test-Report-2023.11.28.13.35.23.html"