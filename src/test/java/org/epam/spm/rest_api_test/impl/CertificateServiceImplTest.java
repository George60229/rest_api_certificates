package org.epam.spm.rest_api_test.impl;

import esm.converter.CertificateConverter;
import esm.converter.TagConverter;
import esm.dto.request.*;
import esm.exception.AppNotFoundException;
import esm.exception.BadRequestException;
import esm.model.GiftCertificate;
import esm.model.Tag;
import esm.repository.CertificateRepository;
import esm.repository.TagRepository;
import esm.service.CertificateService;
import esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceImplTest {

    @Test
    public void first() {

        List<GiftCertificate> certificates = new ArrayList<>();
        List<CertificateRequestDTO> expectedTags = new ArrayList<>();

        CertificateRequestDTO requestDTO = new CertificateRequestDTO();
        expectedTags.add(requestDTO);
        requestDTO.setName("Hello");

        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("Hello");
        giftCertificate.setDescription("WOW");
        giftCertificate.setCertificateId(5);

        CertificateFindByRequestDTO certificateFindByRequestDTO = new CertificateFindByRequestDTO();

        Tag tag = new Tag();
        tag.setName("AMAZING");
        tag.setId(10);
        giftCertificate.setTag(tag);

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        Tag resTag = new Tag();
        resTag.setId(5);
        resTag.setName("Hello");

        certificates.add(giftCertificate);


        Pageable pageable = PageRequest.of(0, 5);

        CertificateRepository mock = Mockito.mock(CertificateRepository.class);
        when(mock.findById(5)).thenReturn(Optional.of(giftCertificate));
        // when(mock.findAll()).thenReturn(certificates);
        //when(mock.findByNameLike("He")).thenReturn(certificates);
        //when(mock.findByDescriptionLike("WOW")).thenReturn(certificates);

        when(mock.findByTagsName("AMAZING")).thenReturn(certificates);
        when(mock.countByTagsName()).thenReturn(tags);

        when(mock.findAll()).thenReturn(certificates);
        when(mock.save(giftCertificate)).thenReturn(giftCertificate);


        TagRepository tagMock = Mockito.mock(TagRepository.class);
        //when(tagMock.findByName("AMAZING")).thenReturn(tags);
        //when(tagMock.findAll()).thenReturn(tags);


        CertificateService certificateService = new CertificateServiceImpl(mock, tagMock);
        CertificateConverter converter = new CertificateConverter();
        converter.setTagConverter(new TagConverter());
        certificateService.setConverter(converter, new TagConverter());

        Assertions.assertEquals(certificateService.getCertificateById(5).getName(), "Hello");
        Assertions.assertEquals(certificateService.findByTagName("AMAZING", pageable).getTotalElements(), certificates.size());
        Assertions.assertEquals(certificateService.popularTag().getName(), tag.getName());
        Assertions.assertEquals(certificateService.listCertificates(certificateFindByRequestDTO, pageable).getTotalElements(), new PageImpl<>(certificates).getTotalElements());
        Assertions.assertEquals(certificateService.listCertificates(null, pageable).getTotalElements(), new PageImpl<>(certificates).getTotalElements());
        Assertions.assertEquals(certificateService.editCertificate(requestDTO, 5).getName(), giftCertificate.getName());


        Throwable thrown = Assertions.assertThrows(AppNotFoundException.class, () -> {
            certificateService.getCertificateById(8);
        });
        Assertions.assertNotNull(thrown.getMessage());

        CertificateEditRequestDto editDto = new CertificateEditRequestDto("Bye", null, 5);
        Assertions.assertThrows(BadRequestException.class, () -> {
            certificateService.editOneField(editDto);
        });



    }
}
