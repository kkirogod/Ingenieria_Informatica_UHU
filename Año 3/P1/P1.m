% EJC 1

imfinfo('P1_1.jpg')

% EJC 2

Imagen1 = imread("P1_1.jpg")

% EJC 3

imshow(Imagen1)

% EJC 4

whos("Imagen1")

% EJC 5

vmax = max(Imagen1(:))

% EJC 6

Imagen2 = 255 - Imagen1
imwrite(uint8(Imagen2), 'ImagenComp.jpg')

% EJC 7

Imagen3 = Imagen1(:,:,1)

% EJC 8

Imagen4 = imadjust(Imagen1,[],[],0.5)
Imagen5 = imadjust(Imagen1,[],[],1.5)

figure, imhist(Imagen4)
figure, imhist(Imagen5)

% EJC 9

Imagen6 = imabsdiff(Imagen4, Imagen5)
Imagen7 = Imagen4-Imagen5
figure, imshow(Imagen6)
figure, imshow(Imagen7)

varLogica = funcion_compara_matrices(Imagen6, Imagen7)

% EJC 10

h1 = funcion_histograma1(Imagen1(:,:,2));
h2 = funcion_histograma2(Imagen1(:,:,2));
