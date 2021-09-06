# Comparing JSON documents in Java with JSON-P

## JSON Patch
[JSON Patch](https://tools.ietf.org/html/rfc6902) is a format for expressing a sequence of operations to be applied to a JSON document. It is defined in the RFC 6902 and is identified by the _application/json-patch+json_ media type.

The JSON Patch document represents an array of objects and each object represents a single operation to be applied to the target JSON document.

The evaluation of a JSON Patch document begins against a target JSON document and the operations are applied sequentially in the order they appear in the array. Each operation in the sequence is applied to the target document and the resulting document becomes the target of the next operation. The evaluation continues until all operations are successfully applied or until an error condition is encountered.

The operation objects must have exactly one op member, whose value indicates the operation to perform:

| Operation | Description  |
|---|---|
| add  | Adds the value at the target location; if the value exists in the given location, it’s replaced  |
| remove  | Removes the value at the target location  |
| replace  | Replaces the value at the target location  |
| move  | Removes the value at a specified location and adds it to the target location  |
| copy  | Copies the value at a specified location to the target location  |
| test  | Tests that a value at the target location is equal to a specified value  |

Any other values are considered errors.

## JSON Merge Patch
[JSON Merge Patch](https://tools.ietf.org/html/rfc7396) is a format that describes the changes to be made to a target JSON document using a syntax that closely mimics the document being modified. It is defined in the RFC 7396 is identified by the _application/merge-patch+json_ media type.

The server processing a JSON Merge Patch document determine the exact set of changes being requested by comparing the content of the provided patch against the current content of the target document:

If the merge patch contains members that do not appear within the target document, those members are added.
If the target does contain the member, the value is replaced.
null values in the merge patch indicate that existing values in the target document are to be removed.
Other values in the target document will remain untouched.

## JSON-P: Java API for JSON Processing
https://javaee.github.io/jsonp/index.html

https://javadoc.io/doc/javax.json/javax.json-api/latest/javax/json/Json.html#createDiff-javax.json.JsonStructure-javax.json.JsonStructure- 
