<?php

namespace EleveBundle\Controller;

use EleveBundle\Entity\eleve;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;


/**
 * Eleve controller.
 *
 * @Route("eleve")
 */
class eleveController extends Controller
{
    /**
     * Lists all eleve entities.
     *
     * @Route("/", name="eleve_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $eleves = $em->getRepository('EleveBundle:eleve')->findAll();

        return $this->render('@Eleve/eleve/index.html.twig', array(
            'eleves' => $eleves,
        ));
    }

    /**
     * Creates a new eleve entity.
     *
     * @Route("/new", name="eleve_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $currentUser = $this->container->get('security.token_storage')->getToken()->getUser();
        $eleve = new Eleve();
        $form = $this->createForm('EleveBundle\Form\eleveType', $eleve);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $eleve->setUser($currentUser);
            $eleve->setNbreAbsence(0);
            $eleve->uploadProfilePicture();
            $em->persist($eleve);
            $em->flush();

            return $this->redirectToRoute('eleve_index', array('id' => $eleve->getId()));
        }

        return $this->render('@Eleve/eleve/new.html.twig', array(
            'eleve' => $eleve,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a eleve entity.
     *
     * @Route("/{id}", name="eleve_show")
     * @Method("GET")
     */
    public function showAction(eleve $eleve)
    {
        $deleteForm = $this->createDeleteForm($eleve);

        return $this->render('@Eleve/eleve/show.html.twig', array(
            'eleve' => $eleve,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing eleve entity.
     *
     * @Route("/{id}/edit", name="eleve_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, eleve $eleve)
    {
        $em = $this->getDoctrine()->getManager();

        $eleves = $em->getRepository('EleveBundle:eleve')->find($eleve);
        $eleves->setNbreAbsence($eleves->getNbreAbsence()+1);
        $em->persist($eleve);
        $em->flush();
        return $this->redirectToRoute('eleve_index', array('id' => $eleve->getId()));

        }




    /**
     * Deletes a eleve entity.
     *
     * @Route("/{id}", name="eleve_delete")
     * @Method("DELETE")
     */
    public function deleteAction( $id)
    {
        $em = $this->getDoctrine()->getManager();
        $conge = $em->getRepository(eleve::class)->find($id);

        $em->remove($conge);
        $em->flush();
        return $this->redirectToRoute('eleve_index');
        }




    /**
     * Creates a form to delete a eleve entity.
     *
     * @param eleve $eleve The eleve entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(eleve $eleve)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('eleve_delete', array('id' => $eleve->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

}
