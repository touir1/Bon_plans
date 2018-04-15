@echo off
call git pull
if ERRORLEVEL 1 goto error
call git add *
if ERRORLEVEL 1 goto error
call git reset -- Bon_plans_desktop_app/src/resources/common/properties/database.config.properties
call git reset -- Bon_plans_desktop_app/src/resources/common/properties/upload.server.config.properties
set /p message="Enter commit message: "
call git commit -m "%message%"
if ERRORLEVEL 1 goto error
call git push
if ERRORLEVEL 1 goto error

COLOR 0A
pause
exit
:error
COLOR 0C
pause
