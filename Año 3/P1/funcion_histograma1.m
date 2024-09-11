function h = funcion_histograma1(I)

    [numFilas , numColumas] = size(I);
    
    h = zeros(1, 256);

    for i=1:numFilas
        for j=1:numColumas

            k = double(I(i,j))+1;
            h(k) = h(k) + 1;

        end
    end

end