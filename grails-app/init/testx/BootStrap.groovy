package testx

import com.prodoffer.Offer
import com.prodoffer.Product
import com.prodoffer.Supplier

class BootStrap {

    def init = { servletContext ->
        def s1 = new Supplier(name:'Supplier1').save(failOnError:true)
        def s2 = new Supplier(name:'Supplier2').save(failOnError:true)
        def s3 = new Supplier(name:'Supplier3').save(failOnError:true)
        
        def p1 = new Product(name:'ABC',supplier:s1.name,volume:110).save(failOnError:true)
        def p2 = new Product(name:'A123',supplier:s2.name,volume:45).save(failOnError:true)
        def p3 = new Product(name:'D567',supplier:s3.name,volume:350).save(failOnError:true)
        
        System.out.println("Product ID: " + p1.id)
        System.out.println("Product ID: " + p2.id)
        System.out.println("Product ID: " + p3.id)
        
        def o1 = new Offer(productId:p1.id, product:p1.name, supplier: p1.supplier, volume:p1.volume).save(failOnError:true)
        def o2 = new Offer(productId:p2.id, product:p2.name, supplier: p2.supplier, volume:p2.volume).save(failOnError:true)
        def o3 = new Offer(productId:p3.id, product:p3.name, supplier: p3.supplier, volume:p3.volume).save(failOnError:true)
        def o4 = new Offer(productId:p2.id, product:p2.name, supplier: p2.supplier, volume:p2.volume).save(failOnError:true)
        def o5 = new Offer(productId:p3.id, product:p3.name, supplier: p3.supplier, volume:p3.volume).save(failOnError:true)
        def o6 = new Offer(productId:p3.id, product:p3.name, supplier: p3.supplier, volume:p3.volume).save(failOnError:true)

        // Had to initiate productID separately b'cause the constructor didn't take it.
        // This makes me almost paranoid!
        o1.productID = p1.id
        o2.productID = p2.id
        o3.productID = p3.id
        o4.productID = p2.id
        o5.productID = p3.id
        o6.productID = p3.id
        
                System.out.println("Offer Product ID: " + o1.productID)
                System.out.println("Offer Product ID: " + o2.productID)
                System.out.println("Offer Product ID: " + o3.productID)
                System.out.println("Offer Product ID: " + o4.productID)
                System.out.println("Offer Product ID: " + o5.productID)
                System.out.println("Offer Product ID: " + o6.productID)

    }
    def destroy = {
    }
}
