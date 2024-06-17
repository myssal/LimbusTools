rem bulk rename certain file extension in directory(include subfolder)
@ECHO OFF
PUSHD .
FOR /R %%d IN (.) DO (
    cd "%%d"
	rem remove .txt extension of atlas file
    IF EXIST *.txt (
       REN *.txt *.
    )
	IF EXIST *.prefab (
       REN *.prefab *.
    )
	rem append .json extension for skel file
	IF EXIST *. (
       REN *. *.json
    )
)
rem POP