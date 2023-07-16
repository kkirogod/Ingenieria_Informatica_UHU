#include <iostream>
#include <stdlib.h>
#include <conio.h>

#define N 30

using namespace std;

class control
{
    int pantalla[N][N];
    int f, c, o; //si o == 1, el programa terminará

public:
    control();
    int keylog();
    void keysearch();
    void showscreen();
};

control::control()
{
    f = c = 0;

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
        {
            if (i == (N/2) && j == (N/2))
                pantalla[i][j] = 1;

            else
                pantalla[i][j] = 0;
        }
}

int control::keylog()
{
    char key;

    key = getch();

    switch (key)
    {
    case 'a':
        f = o = 0;
        c = -1;

        break;

    case 's':
        f = 1;
        c = o = 0;

        break;

    case 'd':
        f = o = 0;
        c = 1;

        break;

    case 'w':
        f = -1;
        c = o = 0;

        break;

    case 'o':
        f = c = 0;
        o = 1;

    default:
        f = c = 0;
    }

    return o;
}

void control::keysearch()
{
    int i, j;
    bool found = false;

    i = 0;
    while (i < N && !found)
    {
        j = 0;
        while (j < N && !found)
        {
            if (pantalla[i][j] == 1)
            {
                found = true;
                pantalla[i][j] = 0;

                if ((i + f) >= N || (i + f) < 0 || (j + c) >= N || (j + c) < 0)
                    pantalla[i + f][j + c] = 0;

                else
                    pantalla[i + f][j + c] = 1;
            }
            else
                j++;
        }
        if (!found)
            i++;
    }
}

void control::showscreen()
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
        {
            if (pantalla[i][j] == 0)
                cout << "  ";

            else
                cout << " O ";

            if (j == N-1)
                cout << endl;
        }
}

int main()
{
    control test;

    while (test.keylog() == 0)
    {
        system("cls");
        test.showscreen();
        test.keylog();
        test.keysearch();
    }

    return 0;
}
