<?php

namespace EleveBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
class eleveType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('date_naissance', DateType::class, [
            'widget' => 'single_text',
            // this is actually the default format for single_text
            'format' => 'yyyy-MM-dd',
        ])
            ->add('prenom')
            ->add('niveau',ChoiceType::class,['choices'=>[
                'Preparatoire'=>'Pretaratoire', 'Garderie'=>'Garderie', 'Krech'=>'Krech', 'Pre preparatoire'=>'Pre preparatoire',
            ],])
            ->add('age')
            ->add('adresse')
            ->add('file');
           # ->add('nbreAbsence')
           # ->add('user');

    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EleveBundle\Entity\eleve'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'elevebundle_eleve';
    }


}
