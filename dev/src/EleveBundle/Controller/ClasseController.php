<?php

namespace EleveBundle\Controller;

use EleveBundle\Entity\Classe;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Classe controller.
 *
 * @Route("classe")
 */
class ClasseController extends Controller
{
    /**
     * Lists all classe entities.
     *
     * @Route("/", name="classe_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $classes = $em->getRepository('EleveBundle:Classe')->findAll();

        return $this->render('@Eleve/classe/index.html.twig', array(
            'classes' => $classes,
        ));
    }

    /**
     * Creates a new classe entity.
     *
     * @Route("/new", name="classe_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $classe = new Classe();
        $form = $this->createForm('EleveBundle\Form\ClasseType', $classe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $classe->setNbreEleve(0);
            $em->persist($classe);
            $em->flush();

            return $this->redirectToRoute('classe_index', array('id' => $classe->getId()));
        }

        return $this->render('@Eleve/classe/new.html.twig', array(
            'classe' => $classe,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a classe entity.
     *
     * @Route("/{id}", name="classe_show")
     * @Method("GET")
     */
    public function showAction(Classe $classe)
    {
        $deleteForm = $this->createDeleteForm($classe);

        return $this->render('@Eleve/classe/show.html.twig', array(
            'classe' => $classe,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing classe entity.
     *
     * @Route("/{id}/edit", name="classe_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Classe $classe)
    {
        $deleteForm = $this->createDeleteForm($classe);
        $editForm = $this->createForm('EleveBundle\Form\ClasseType', $classe);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('classe_index', array('id' => $classe->getId()));
        }

        return $this->render('@Eleve/classe/edit.html.twig', array(
            'classe' => $classe,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a classe entity.
     *
     * @Route("/{id}", name="classe_delete")
     * @Method("DELETE")
     */
    public function deleteCAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $classe= $em->getRepository(Classe::class)->find($id);

        $em->remove($classe);
        $em->flush();
        return $this->redirectToRoute('classe_index');
    }

    /**
     * Creates a form to delete a classe entity.
     *
     * @param Classe $classe The classe entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Classe $classe)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('classe_delete', array('id' => $classe->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
