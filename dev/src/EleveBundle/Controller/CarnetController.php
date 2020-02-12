<?php

namespace EleveBundle\Controller;

use EleveBundle\Entity\Carnet;
use EleveBundle\Entity\eleve;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;
use UserBundle\Entity\User;

/**
 * Carnet controller.
 *
 * @Route("carnet")
 */
class CarnetController extends Controller
{
    /**
     * Lists all carnet entities.
     *
     * @Route("/", name="carnet_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $carnets = $em->getRepository('EleveBundle:Carnet')->findAll();

        return $this->render('@Eleve/carnet/index.html.twig', array(
            'carnets' => $carnets,
        ));
    }

    /**
     * Creates a new carnet entity.
     *
     * @Route("/new", name="carnet_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $carnet = new Carnet();
        $form = $this->createForm('EleveBundle\Form\CarnetType', $carnet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($carnet);
            $em->flush();

            return $this->redirectToRoute('carnet_index', array('id' => $carnet->getId()));
        }

        return $this->render('@Eleve/carnet/new.html.twig', array(
            'carnet' => $carnet,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a carnet entity.
     *
     * @Route("/{id}", name="carnet_show")
     * @Method("GET")
     */
    public function showAction(Carnet $carnet)
    {
        $deleteForm = $this->createDeleteForm($carnet);

        return $this->render('@Eleve/carnet/show.html.twig', array(
            'carnet' => $carnet,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    public function show2Action()
    {
        $currentUser = $this->container->get('security.token_storage')->getToken()->getUser();
        $em=$this->getDoctrine()->getManager();
        $parent=$em->getRepository(User::class)->find($currentUser);

        $kids= $em->getRepository(eleve::class)->findBy(array('user'=>$parent));

        for($i=0;$i<count($kids); $i++ ){
            $valeur[$i]=$em->getRepository(Carnet::class)->findOneBy(array('eleve'=>$kids[$i]->getId()));

        }
        return $this->render('@Eleve/carnet/show2.html.twig', array(
            'valeur' => $valeur,

        ));
    }

    /**
     * Displays a form to edit an existing carnet entity.
     *
     * @Route("/{id}/edit", name="carnet_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Carnet $carnet)
    {
        $deleteForm = $this->createDeleteForm($carnet);
        $editForm = $this->createForm('EleveBundle\Form\CarnetType', $carnet);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('carnet_index', array('id' => $carnet->getId()));
        }

        return $this->render('@Eleve/carnet/edit.html.twig', array(
            'carnet' => $carnet,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a carnet entity.
     *
     * @Route("/{id}", name="carnet_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Carnet $carnet)
    {
        $form = $this->createDeleteForm($carnet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($carnet);
            $em->flush();
        }

        return $this->redirectToRoute('carnet_index');
    }

    /**
     * Creates a form to delete a carnet entity.
     *
     * @param Carnet $carnet The carnet entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Carnet $carnet)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('carnet_delete', array('id' => $carnet->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
