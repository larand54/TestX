package com.prodoffer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def offer = null//OfferDetail.list()
        def List<String> millList = getMills()
        [products: getBufferList(), offer: offer, millList: millList]

    }
    def availableProducts() {
        System.out.println(params)
        def List<Product> products = getBufferList()
        render(template:"AvailableProductData", model:[products: products])
    }
    def listOffers(){
        System.out.println("#--#"+params)
        def List<Offer> offers = getOfferList()
        System.out.println("OfferDetail filtered count: "+offers.size) 
        render(template:"ListOffers", model:[offers: offers])
    }
    def List<String> getMills() {
        System.out.println("getMills <<<<")
        Product.executeQuery("SELECT DISTINCT supplier FROM Product")
    }
    
    def List<Offer> getOfferList() {
        System.out.println(" getOfferList>>>" + params)
        def int id = params.id.toInteger()
        System.out.println(" getOfferList>>>ID: " + id)
        def ol = Offer.list()
        System.out.println("Offer count total: " + ol.size)
        return ol.findAll({it.productID==id})
    }
    def List<Product> getBufferList() {
        def prodList = Product.list()
        def List<Product> myList
        def List<Product> tempList
        System.out.println(" GetBufferList>>>" + params)
        if ((params.supplier != null) && (params.supplier != '')) {
            tempList = prodList.findAll({it.supplier==params.supplier}) 
            System.out.println("Filtered")
        }
        return tempList?tempList:prodList
    }

    def show(Product product) {
        respond product
    }

    def create() {
        respond new Product(params)
    }

    @Transactional
    def save(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors, view:'create'
            return
        }

        product.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*' { respond product, [status: CREATED] }
        }
    }

    def edit(Product product) {
        respond product
    }

    @Transactional
    def update(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors, view:'edit'
            return
        }

        product.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*'{ respond product, [status: OK] }
        }
    }

    @Transactional
    def delete(Product product) {

        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        product.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
