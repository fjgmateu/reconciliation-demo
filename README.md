# Packlink Engineering coding test

## Instructions

1. Fork and Clone this repo
2. Work on the requirements
3. Do a PR with your code and any other needed information
4. Send an email notifying us

### What we will look for
We want to see your proficency writing code. How you structre it, how you test.
While a working application is a big plus, if not working please submit it anyway.

### Technology Requirements

- JVM language (Java and/or Scala preferred)
- Use of a framework (Spring+Boot preferred)
- Tests
- The application has to be self-contained. That is, should not depend on extenernal conections not provided with it.

### Storage
Use whatever storage you find suitable for this test. Remember that the 
application has to be self-contained.

## Context of the problem to solve
We work with multiple logistic services providers. They scan the parcels sent
with Packlink. Some times there's a difference between what our clients declare 
and what the provider scans. 

This is called a "weight deviation".

### The task at hand

Create a microservice to recover scanned information from a Carrier and **dispatch** a `reconciliation.item.parse.success`
event when appropiate.

The information from the carriers can be tricky. In the Resources section you will
find examples of tracking information.

The microservice will be receiving events in the form of POST requests:

- When there is a succesfull integration with a carrier: `carrier.order.success`

  to the route: `http://localhost:8055/events/carrier_success` (see more in the [resources](#resources) section)
  With the information of this event the application should call the carrier endpoint for tracking information (see below).

- For the sake of this exercise we will assume that the carrier endpoint is `https://example.com/tracking/{tracking_number}`
 (where `{tracking_number}` is a placeholder for the actual tracking_number for that shipment, found in the
  `carrier.order.success` event above).

**The microservice should**:

- Listen to port 8055
- Have HTTP endpoints for receiving the events (see above)
- Have HTTP endpoint/s to
  - retrieve received shipments in the route `http://localhost:8055/shipments`, with payload like:
    ```javascript
      {"shipments":[
        {
          "reference":"ABCD123456",
          "parcels":1,
          "state":<states>
        }
       ]
      }
    ```
    where *states* can be:

      - "pending" : Reconciliation pending, we received a `carrier.order.success` event for that shipment

      - "sent_for_concilliation" : Request for reconciliation by dispatching an event (see below)

- Simulate the carrier endpoint, returning the sample data ([examples](#resources)).
- Dispatch `reconciliation.item.parse.success` event.

  Every carrier has it's own states. In the [resources/tracking](/resources/tracking/) folder of this repo you will find examples of tracking information for one carrier, with a "final" state
  to use as flag to trigger the concilliation event dispatch.
  
  *Please provide an implementation in a way that would be extensible to add more carriers and it's final states*
  
  Can be an application event or a fake publisher.

And remember, things can go wrong. Information can be incomplete and systems will fail.

#### Resources

##### Payload of `reconciliation.item.parse.success` event

```javascript
{
  "event":"reconciliation.item.parse.sucess",
  "reference":"ABCD123456",
  "parcels":1,
  "weight":0.5
}
```

##### Carrier's scanned information is available in a JSON+REST API. 

Final state for UPS carrier is `DELIVERED`

See [here](/resources/tracking/ups) 3 examples.

##### Events

1. `carrier.order.success`
When a shipment has been succesfully integrated with a given carrier.
```javascript
{
  "packlink_reference":"ABCD123456",
  "parcels" : [
  {
    "weight":1,
    "width": 10,
    "height": 10,
    "lenght": 10
  }
  ],
  "carrier":"UPS",
  "service_id":"28123",
  "integration_code":"UPS_ES_A",
  "traking_numbers":["XYZ123"]
}
```
See [here](/resources/carrier_success_events) 3 examples.
