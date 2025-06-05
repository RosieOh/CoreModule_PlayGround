package com.core.common.model.modules;

import java.util.Arrays;

import com.core.common.model.ModuleInfo;

public class FileModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "File",
            Arrays.asList(
                "파일 업로드/다운로드",
                "이미지 처리",
                "파일 압축/해제",
                "파일 변환",
                "대용량 파일 처리",
                "파일 보안",
                "파일 메타데이터 관리"
            ),
            """
            // 1. 파일 업로드 설정
            @Configuration
            public class FileConfig {
                @Bean
                public MultipartConfigElement multipartConfigElement() {
                    MultipartConfigFactory factory = new MultipartConfigFactory();
                    factory.setMaxFileSize(DataSize.ofMegabytes(10));
                    factory.setMaxRequestSize(DataSize.ofMegabytes(10));
                    return factory.createMultipartConfig();
                }
            }
            
            // 2. 파일 업로드 처리
            @Service
            public class FileService {
                public FileInfo uploadFile(MultipartFile file) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    Path targetLocation = fileStorageLocation.resolve(fileName);
                    
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    
                    return FileInfo.builder()
                        .fileName(fileName)
                        .fileType(file.getContentType())
                        .size(file.getSize())
                        .build();
                }
            }
            
            // 3. 이미지 처리
            @Service
            public class ImageService {
                public void processImage(String fileName) {
                    BufferedImage originalImage = ImageIO.read(new File(fileName));
                    BufferedImage resizedImage = Scalr.resize(originalImage, 800);
                    
                    ImageIO.write(resizedImage, "jpg", new File("resized-" + fileName));
                }
            }
            
            // 4. 파일 압축
            @Service
            public class CompressionService {
                public void compressFiles(List<String> fileNames, String zipFileName) {
                    try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFileName))) {
                        for (String fileName : fileNames) {
                            File fileToZip = new File(fileName);
                            FileInputStream fis = new FileInputStream(fileToZip);
                            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                            zipOut.putNextEntry(zipEntry);
                            
                            byte[] bytes = new byte[1024];
                            int length;
                            while ((length = fis.read(bytes)) >= 0) {
                                zipOut.write(bytes, 0, length);
                            }
                        }
                    }
                }
            }
            
            // 5. 파일 보안
            @Service
            public class FileSecurityService {
                public void validateFile(MultipartFile file) {
                    String contentType = file.getContentType();
                    if (!allowedContentTypes.contains(contentType)) {
                        throw new FileValidationException("Unsupported file type");
                    }
                    
                    if (file.getSize() > maxFileSize) {
                        throw new FileValidationException("File size exceeds limit");
                    }
                }
            }
            """,
            """
            # File 설정
            file.upload-dir=./uploads
            file.max-size=10485760
            file.allowed-types=image/jpeg,image/png,application/pdf
            file.image.max-width=1920
            file.image.max-height=1080
            file.compression.enabled=true
            file.compression.quality=0.8
            file.security.scan-enabled=true
            file.metadata.enabled=true
            file.storage.type=local
            """,
            Arrays.asList("security", "validation"),
            Arrays.asList(
                "파일 업로드 크기 제한을 적절히 설정해야 합니다.",
                "허용된 파일 형식을 명확히 정의해야 합니다.",
                "이미지 처리는 성능을 고려하여 최적화해야 합니다.",
                "파일 압축은 용량과 품질의 균형을 고려해야 합니다.",
                "대용량 파일은 청크 단위로 처리해야 합니다.",
                "파일 보안 검사를 철저히 수행해야 합니다.",
                "파일 메타데이터는 체계적으로 관리해야 합니다."
            )
        );
    }
} 