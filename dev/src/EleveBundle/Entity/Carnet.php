<?php

namespace EleveBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Carnet
 *
 * @ORM\Table(name="carnet")
 * @ORM\Entity(repositoryClass="EleveBundle\Repository\CarnetRepository")
 */
class Carnet
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
     * @ORM\ManyToOne(targetEntity="eleve")
     *
     * @ORM\JoinColumn(name="eleve_id", referencedColumnName="id" )
     */
    private $eleve;

    /**
     * @return mixed
     */
    public function getEleve()
    {
        return $this->eleve;
    }

    /**
     * @param mixed $eleve
     */
    public function setEleve($eleve)
    {
        $this->eleve = $eleve;
    }
    /**
     * @var float
     *
     * @ORM\Column(name="note", type="float")
     */
    private $note;

    /**
     * @var string
     *
     * @ORM\Column(name="appreciation", type="string", length=255)
     */
    private $appreciation;
    /**
     * @var string
     *
     * @ORM\Column(name="semestre", type="string", length=255)
     */
    private $semestre;

    /**
     * @return string
     */
    public function getSemestre()
    {
        return $this->semestre;
    }

    /**
     * @param string $semestre
     */
    public function setSemestre($semestre)
    {
        $this->semestre = $semestre;
    }

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date")
     */
    private $date;


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
     * Set note
     *
     * @param float $note
     *
     * @return Carnet
     */
    public function setNote($note)
    {
        $this->note = $note;

        return $this;
    }

    /**
     * Get note
     *
     * @return float
     */
    public function getNote()
    {
        return $this->note;
    }

    /**
     * Set appreciation
     *
     * @param string $appreciation
     *
     * @return Carnet
     */
    public function setAppreciation($appreciation)
    {
        $this->appreciation = $appreciation;

        return $this;
    }

    /**
     * Get appreciation
     *
     * @return string
     */
    public function getAppreciation()
    {
        return $this->appreciation;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return Carnet
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }
}

