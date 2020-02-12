<?php

namespace EleveBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Classe
 *
 * @ORM\Table(name="classe")
 * @ORM\Entity(repositoryClass="EleveBundle\Repository\ClasseRepository")
 */
class Classe
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_classe", type="string", length=255)
     */
    private $nomClasse;

    /**
     * @var int
     *
     * @ORM\Column(name="nbre_eleve", type="integer")
     */
    private $nbreEleve;

    /**
     * @var int
     *
     * @ORM\Column(name="capacite", type="integer")
     */
    private $capacite;

    /**
     * @var string
     *
     * @ORM\Column(name="salle", type="string", length=255)
     */
    private $salle;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nomClasse
     *
     * @param string $nomClasse
     *
     * @return Classe
     */
    public function setNomClasse($nomClasse)
    {
        $this->nomClasse = $nomClasse;

        return $this;
    }

    /**
     * Get nomClasse
     *
     * @return string
     */
    public function getNomClasse()
    {
        return $this->nomClasse;
    }

    /**
     * Set nbreEleve
     *
     * @param integer $nbreEleve
     *
     * @return Classe
     */
    public function setNbreEleve($nbreEleve)
    {
        $this->nbreEleve = $nbreEleve;

        return $this;
    }

    /**
     * Get nbreEleve
     *
     * @return int
     */
    public function getNbreEleve()
    {
        return $this->nbreEleve;
    }

    /**
     * Set capacite
     *
     * @param integer $capacite
     *
     * @return Classe
     */
    public function setCapacite($capacite)
    {
        $this->capacite = $capacite;

        return $this;
    }

    /**
     * Get capacite
     *
     * @return int
     */
    public function getCapacite()
    {
        return $this->capacite;
    }

    /**
     * Set salle
     *
     * @param string $salle
     *
     * @return Classe
     */
    public function setSalle($salle)
    {
        $this->salle = $salle;

        return $this;
    }

    /**
     * Get salle
     *
     * @return string
     */
    public function getSalle()
    {
        return $this->salle;
    }
}

