package com.example.sof3062.B2_RestfullAPI.util;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public class MapperUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public static <S, D> D map(S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    /**
     * Dùng cho update: copy field từ source sang object target đã tồn tại
     */
    public static <S, T> void mapToExisting(S source, T target) {
        modelMapper.map(source, target);
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
