export class Trip {

    id: number;
    leaveDate: string;
    leaveFrom: string;
    to: string;
    user: number;

    constructor(leaveDate: string, leaveFrom:string, to:string, user:number){
        this.leaveDate = leaveDate;
        this.leaveFrom = leaveFrom;
        this.to = to;
        this.user = user;
    }
}
