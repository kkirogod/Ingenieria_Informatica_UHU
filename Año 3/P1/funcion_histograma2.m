function h = funcion_histograma2(I)
    
    h = zeros(1, 256);

    for i=1:256
        h(i) = sum(sum(I==(i-1)));
    end

end