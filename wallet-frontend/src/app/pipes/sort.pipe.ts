import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sort'
})
export class SortPipe implements PipeTransform {

  transform(values: any[],field:string = "id",sortOrder:string="asc"): any[] {
    console.log("sort on:"+ field);
    values.sort(
      (o1:any,o2:any)=>{
        if(o1[field] > o2[field])
        return 1;
        if(o1[field] < o2[field])
        return -1;        
        return 0;
      }
    );

    return sortOrder === 'asc' ? values:values.reverse();
  }

}
