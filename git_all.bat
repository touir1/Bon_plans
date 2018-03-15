@echo off
call git pull
if ERRORLEVEL 1 goto error
call git add *
if ERRORLEVEL 1 goto error
set /p message="Enter commit message: "
call git commit -m "%message%"
if ERRORLEVEL 1 goto error
call git push
if ERRORLEVEL 1 goto error

pause
exit
:error
COLOR 04
pause