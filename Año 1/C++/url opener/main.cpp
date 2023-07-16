#include <windows.h>
#include <shellapi.h>
using namespace std;

int main()
{
    char url[100] = "https://es.google.com/";
    for (int i=0; i<50; i++)
    {
        Sleep(5);
        ShellExecute(0, 0, url, 0, 0, SW_SHOW);
    }

    return 0;
}
